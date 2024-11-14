fetch('/html/footer.html')
.then(response => response.text())
.then(data => {
    document.getElementById('footer').innerHTML = data;
});

fetch('/html/nav.html')
.then(response => response.text())
.then(data => {
    document.getElementById('nav').innerHTML = data;
});

fetch('/html/logmain.html')
.then(response => response.text())
.then(data => {
    document.getElementById('logmain').innerHTML = data;
});

