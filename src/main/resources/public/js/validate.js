$(document).ready(function() {
	$.ajax({
		url : "/review/validate",
		type : "GET",
		async:false,
		success : function(response) {
			console.log(response);
			var trHTML = '';
			var tmpl = $.templates("#tablerow");
			$.each(response, function(i, item) {
				var html = tmpl.render(item);
				trHTML += html
			});
			$('#imagesTable').append(trHTML);
		}
	});
	
	// Get the modal
	var modal = document.getElementById('myModal');

	// Get the image and insert it inside the modal - use its "alt" text as a caption
	var modalImg = document.getElementById("img01");
	var captionText = document.getElementById("caption");
	$("img").on('click' ,function() {
		modal.style.display = "block";
		modalImg.src = this.src;
		modalImg.alt = this.alt;
		captionText.innerHTML = this.alt;
	});

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
		modal.style.display = "none";
	}
});