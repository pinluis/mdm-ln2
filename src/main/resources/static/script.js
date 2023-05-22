document.getElementById("submitButton").addEventListener("click", function () {
  var fileInput = document.getElementById("fileInput");
  var file = fileInput.files[0];

  if (!file) {
    alert("Please select a PNG file before clicking submit.");
    return;
  }

  var formData = new FormData();
  formData.append("file", file);

  // Display the original image
  var originalImageElement = document.getElementById("originalImage");
  originalImageElement.src = URL.createObjectURL(file);
  originalImageElement.style.display = "block";

  // Show loading spinner
  var loadingSpinner = document.getElementById("loadingSpinner");
  loadingSpinner.style.display = "block";

  fetch("/enhance", {
    method: "POST",
    body: formData,
  })
    .then((response) => response.blob())
    .then((imageBlob) => {
      // Hide loading spinner
      loadingSpinner.style.display = "none";

      // Display the enhanced image
      var enhancedImageElement = document.getElementById("resultImage");
      enhancedImageElement.src = URL.createObjectURL(imageBlob);
      enhancedImageElement.style.display = "block";
    })
    .catch((error) => {
      console.error("Error:", error);
    });
});
