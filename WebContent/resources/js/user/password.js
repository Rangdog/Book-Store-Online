// DISPLAY & HIDE PASSWORD.
const eyeIcons = document.querySelectorAll('.icon-eye')

eyeIcons.forEach(icon => {
    icon.addEventListener('click', e => {
        if (e.target.name == 'eye-off-outline') {
            e.target.name = 'eye-outline'
            e.target.parentElement.querySelector('input').type = 'text'
        } else {
            e.target.name = 'eye-off-outline'
            e.target.parentElement.querySelector('input').type = 'password'
        }
    })
})