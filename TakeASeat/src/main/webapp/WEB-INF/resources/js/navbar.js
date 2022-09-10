const registerButton = document.getElementById("nav-bar__register");
const loginButton = document.getElementById("nav-bar__login");

registerButton.addEventListener('click', ev => switchLoginRegisterClasses(registerButton, loginButton));
loginButton.addEventListener('click', ev => switchLoginRegisterClasses(loginButton, registerButton));


function switchLoginRegisterClasses(buttonClicked, buttonToSwitch) {
}