const showNavbar = (toggleID, navID, bodyID, headerID) => {
    const toggle = document.getElementById(toggleID);
    const nav = document.getElementById(navID);
    const body = document.getElementById(bodyID);
    const header = document.getElementById(headerID);

    if(toggle && nav && body && header) {
        toggle.addEventListener("click", () => {
            nav.classList.toggle("show");
            toggle.classList.toggle("bx-x")
            body.classList.toggle("body-pd");
            header.classList.toggle("body-pd");
        });
    }
}

showNavbar("header-toggle", "nav-bar", "body-pd", "header");

const linkColor = document.querySelectorAll(".nav-link");

function colorLink() {
    if(linkColor) {
        linkColor.forEach(i => i.classList.remove("active"));
        this.classList.add("active");
    }
}

linkColor.forEach(i => i.addEventListener("click", colorLink));