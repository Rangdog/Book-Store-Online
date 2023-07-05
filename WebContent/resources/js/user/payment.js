// GENERAL.
var bodyElement = document.querySelector('body')
var mainElement = document.querySelector('main')
var modalAddress = document.querySelector('#modal-address')


// CLOSE-MODAL BUTTON.
var closeModalButton = document.querySelector('button[name="close"]')

closeModalButton.onclick = function () {
    modalAddress.classList.add('close')
    mainElement.classList.toggle('inactive')
    bodyElement.classList.toggle('lock-scroll')
}

/// OPEN-MODAL BUTTON.
var openModalButton = document.querySelector('button[name="change-address"]')

openModalButton.onclick = function (e) {
    e.preventDefault()
    bodyElement.classList.toggle('lock-scroll')
    mainElement.classList.toggle('inactive')
    modalAddress.classList.remove('close')
}

// ADDRESS INFORMATION.
var usedId = document.getElementById('id')
var usedReceiver = document.getElementById('used-receiver')
var usedPhone = document.getElementById('used-phone')
var usedAddress = document.getElementById('used-address')

var targetButtons = document.querySelectorAll('button[name="target"]')

targetButtons.forEach(button => {
    button.onclick = function (e) {
        var address = e.target.parentElement
        usedId.value = address.querySelector('input[name="id"]').value
        usedReceiver.innerText = address.querySelector('input[name="receiver"]').value
        usedPhone.innerText = address.querySelector('input[name="phone"]').value
        usedAddress.innerText = address.querySelector('textarea[name="address"]').value
        closeModalButton.click()
    }
})