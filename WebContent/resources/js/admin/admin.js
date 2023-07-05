// BUTTON EVENTS.
var controlButtons = document.querySelectorAll('.control-button, .auxiliary-button')
var clickableButtons = Array.from(controlButtons).filter(button => button.id)
var controlPages = Array.from(document.querySelectorAll('main > section'))
var pageTitle = document.querySelector('main > header')

clickableButtons.forEach(button => {
    button.classList.add('unclickedColor')
    // CLICK EVENT.
    button.onclick = function (e) {
        // TURN OFF DISPLAY OF PREVIOUS PAGE.
        controlPages.find(page => page.classList.contains('open')).classList.remove('open')
        // GET SELECTED PAGE NAME & TURN ON DISPLAY OF SELECTED PAGE.
        const selectedPageName = button.id.replace('control', 'page')
        controlPages.find(page => page.id == selectedPageName).classList.add('open')
        // CHANGE PAGE TITLE & COLOR OF SELECTED BUTTON.
        if (button.classList.contains('control-button')) {
            pageTitle.innerText = button.innerText
            controlButtons.forEach(button => button.classList.remove('clickedColor'))
            button.classList.add('clickedColor')
        } else {
            e.preventDefault()
        }
        // CHANGE COLOR OF PARENT BUTTON IF THIS IS A CHILD BUTTON.
        if (button.parentElement.classList.contains('detail-bar')) {
            var parentButton = button.parentElement.parentElement.querySelector('.parent-control')
            parentButton.classList.add('clickedColor')
        }
    }
    // HOVER EVENTS.
    button.onmouseover = function () {
        button.classList.add('hoveredColor')
    }

    button.onmouseleave = function () {
        button.classList.remove('hoveredColor')
    }
})


// BACK BUTTON.
var backButtons = document.querySelectorAll('button[name="back"]')
var supProduct = document.querySelectorAll('.sup-product')
backButtons.forEach(button => {
    button.onclick = function (e) {
        e.preventDefault()
        // CLOSE THE CHILD PAGE & OPEN THE PARENT PAGE.
        var childPage = document.querySelector('.open')
        childPage.classList.remove('open')
        document.getElementById(childPage.id.replace(/-.*-/, '-')).classList.add('open')
    }
})

supProduct.forEach(b => {
	b.onclick = function(e){
		e.preventDefault();
		var childPage = document.querySelector('.open')
        childPage.classList.remove('open')
        document.getElementById('page-product').classList.add('open')
	}
})

// CHECKBOX CLICK EVENT.
var generalCheckboxs = document.querySelectorAll('input[name="general"]')

generalCheckboxs.forEach(checkbox => {
    checkbox.onclick = function (e) {
        // GET THE TABLE.
        var dataTable = e.target.parentElement.parentElement.parentElement
        // GET ALL CHECKBOXS IN SELECTED PAGE.
        var allCheckboxs = dataTable.querySelectorAll('input[type="checkbox"]')
        // MODIFY ALL CHECKBOXS.
        if (e.target.checked) {
            allCheckboxs.forEach(checkbox => checkbox.checked = true)
        } else {
            allCheckboxs.forEach(checkbox => checkbox.checked = false)
        }
    }
})