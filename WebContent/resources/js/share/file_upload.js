// UPLOAD IMAGE FILE
var uploadButtons = document.querySelectorAll('.upload-image')

uploadButtons.forEach(button => {
    button.onchange = function (e) {
        var reader = new FileReader()
        reader.onload = function (file) {
            var imgElement = e.target.parentElement.querySelector('img')
            imgElement.src = file.target.result
        }
        reader.readAsDataURL(e.target.files[0])
    }
})