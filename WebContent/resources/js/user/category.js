// LIMIT THE NUMBER OF FILTER VALUE.
var filterAreas = document.querySelectorAll('#filter-bar > form > div')
var overflowControls = document.querySelectorAll('.overflowControl')
filterAreas.forEach((value, index) => {
    var choiceQuantity = value.querySelectorAll('ul > li, #ranges > div')
    console.log(choiceQuantity)
    if (choiceQuantity.length > 8) {
        overflowControls[index-1].style.display = 'block'
    }
})

overflowControls.forEach(control => {
    control.onclick = function (e) {
        var filterArea = e.target.parentElement.querySelector('ul, div')
        filterArea.classList.toggle('isOverflow')
    }
})