package com.squad.cccreview.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.squad.cccreview.jpa.ReviewRepository;
import com.squad.cccreview.model.BaseResponse;
import com.squad.cccreview.model.Review;

import lombok.extern.log4j.Log4j;

@RestController("/review")
@Log4j
public class ReviewController {
	
	public static final String ROOT = "target";
	
	@Autowired
	private Cloudinary cloudinary;
	
	@Autowired
	private ReviewRepository repo;

	@PostMapping("/")
	public @ResponseBody BaseResponse addReview(@RequestParam("file") MultipartFile file,
			@RequestParam("review") String review,@RequestParam("rating") Double rating) {
		BaseResponse response = new BaseResponse(false, "");
		if (!file.isEmpty()) {
			try {
				Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
				Map clouinaryResponse = cloudinary.uploader().upload(Paths.get(ROOT, file.getOriginalFilename()).toString(), ObjectUtils.emptyMap());
				Review reviewObj = new Review(review,  rating);
				reviewObj.setInvoiceImageUrl((String) clouinaryResponse.get("url"));
				repo.insert(reviewObj);
				response.setSuccess(true);
				response.setMessage("Your review is saved.If the image is genuine, people will read your review");
			} catch (IOException | RuntimeException e) {
				log.error(e);
				response.setMessage("some thing went wrong");
			}
		} else {
		}
		return response;
	}
	
	@GetMapping("/validate")
	public List<Review> getInvoices(){
		return repo.findByBillIdIsNull();
	}
	
	@GetMapping("/")
	public List<Review> search(@RequestParam("search") String searchterm){
		return repo.findByRegex(searchterm);
	}

}
