package com.squad.cccreview.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.github.vbauer.herald.annotation.Log;
import com.squad.cccreview.jpa.ReviewRepository;
import com.squad.cccreview.model.BaseResponse;
import com.squad.cccreview.model.Review;

import ch.qos.logback.classic.Logger;


@RestController
@RequestMapping("/review")
public class ReviewController {

	public static final String ROOT = "target";

	@Autowired
	private Cloudinary cloudinary;

	@Autowired
	private ReviewRepository repo;

	@Log
	private Logger logger;

	@PostMapping("/")
	public @ResponseBody BaseResponse addReview(@RequestParam("file") MultipartFile file,
			@RequestParam("review") String review, @RequestParam("rating") Double rating) {
		BaseResponse response = new BaseResponse(false, "");
		if (!file.isEmpty()) {
			try {
				Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
				Map clouinaryResponse = cloudinary.uploader()
						.upload(Paths.get(ROOT, file.getOriginalFilename()).toString(), ObjectUtils.emptyMap());
				Review reviewObj = new Review(review, rating);
				reviewObj.setBillId(null);
				reviewObj.setInvoiceImageUrl((String) clouinaryResponse.get("url"));
				repo.insert(reviewObj);
				response.setSuccess(true);
				response.setMessage("Your review is saved.If the image is genuine, people will read your review");
				Files.deleteIfExists(Paths.get(ROOT, file.getOriginalFilename()));
			} catch (IOException | RuntimeException e) {
				logger.error(e.getMessage());
				response.setMessage("some thing went wrong");
			}
		} else {
		}
		return response;
	}

	@GetMapping("/validate")
	public List<Review> getInvoices() {
		return repo.findByBillIdIsNull();
	}

	@PostMapping("/validate")
	public @ResponseBody BaseResponse updateReview(
			@RequestParam("reviewId") String review,@RequestParam("company") String company,@RequestParam("billId") String billId) {
		BaseResponse response = new BaseResponse(false, "");
		Review reviewObj = repo.findOne(review);
		if(StringUtils.isEmpty(billId)){
		repo.delete(review);	
		response.setSuccess(true);
		response.setMessage("Review deleted");
		}else{
		reviewObj.setBillId(billId);
		reviewObj.setCompany(company);
		repo.save(reviewObj);
		response.setMessage("Review approved");
		response.setSuccess(true);
		}
		return response;
	}

	@RequestMapping(value = "/search/{term}", method = RequestMethod.GET)
	public List<Review> search(@PathVariable String term) {
		return repo.findByBillIdNotNullAndRegex(term);
	}

}
