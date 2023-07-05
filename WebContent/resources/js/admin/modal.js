//ajax

function renderChinhSach(chinhSach){
	
}
// GENERAL.
var overallElement = document.querySelector('#overall')

// CLOSE-MODAL BUTTON.
var closeModalButtons = document.querySelectorAll('button[name="close"]')

closeModalButtons.forEach(button => {
    button.onclick = function () {
        // INACTIVE THE POP-UP MODAL.
        button.parentElement.parentElement.classList.add('close')
        // ACTIVE THE OVERALL DISPLAY.
        overallElement.classList.toggle('inactive')
    }
})


// OPEN-MODAL BUTTON.
var openModalButtons = document.querySelectorAll('.modal-button')

openModalButtons.forEach(button => {
    button.onclick = function (e) {
        e.preventDefault()
        // INACTIVE THE OVERALL DISPLAY.
        overallElement.classList.toggle('inactive')
        // ACTIVE THE POP-UP MODAL.
        const modalName = document.querySelector('.open').id.replace('page', button.name)
         if(modalName.includes("update")){
             document.getElementById(modalName).lastElementChild.firstElementChild.value = button.parentElement.parentElement.firstElementChild.innerHTML
        }
         if(modalName.includes("block")){
			console.log(button.parentElement.parentElement.parentElement.children[1].innerHTML);
             document.getElementById(modalName).lastElementChild.firstElementChild.firstElementChild.value =button.parentElement.parentElement.parentElement.children[1].innerHTML
        }
        document.getElementById(modalName).classList.remove('close')
    }
})


/*TOAST MESSAGE */
function toast({ title = "", message = "", type = "info", duration = 3000 }) {
  const main = document.getElementById("toast");
  if (main) {
    const toast = document.createElement("div");

    // Auto remove toast
    const autoRemoveId = setTimeout(function () {
      main.removeChild(toast);
    }, duration + 1000);

    // Remove toast when clicked
    toast.onclick = function (e) {
      if (e.target.closest(".toast__close")) {
        main.removeChild(toast);
        clearTimeout(autoRemoveId);
      }
    };

    const icons = {
      success: "checkmark-outline",
      info: "alert-circle-outline",
      warning: "alert-circle-outline",
      error: "alert-circle-outline"
    };
    const icon = icons[type];
    const delay = (duration / 1000).toFixed(2);

    toast.classList.add("toast", `toast--${type}`);
    toast.style.animation = `slideInLeft ease .3s, fadeOut linear 1s ${delay}s forwards`;

    toast.innerHTML = `
                    <div class="toast__icon">
                       <ion-icon name="${icons[type]}"></ion-icon>
                    </div>
                    <div class="toast__body">
                        <h3 class="toast__title">${title}</h3>
                        <p class="toast__msg">${message}</p>
                    </div>
                    <div class="toast__close">
                        <i class="fas fa-times"></i>
                    </div>
                `;
    main.appendChild(toast);
  }
}

