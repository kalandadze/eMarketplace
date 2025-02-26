const ip =window.location.host.replace(":8080","")
function setUp() {
    sessionStorage.setItem("sortAs", "DateDesc");
    loadPage();
}

function loggedSetUp(){
    var username =sessionStorage.getItem("username");
    document.getElementById("username").textContent=username;
    setUp();
}

function logout(){
    sessionStorage.removeItem("username");
    sessionStorage.removeItem("token");
    // window.location='http://localhost:8080'
    window.location=`http://${ip}:8080`;
}

async function loadPage() {
    var page = document.getElementById("pageNum").textContent;
    try {
        document.getElementById("exit").src=`http://${ip}:8080/exit.png`;
        document.getElementById("addListing").onclick=function () {window.location=`http://${ip}:8080/new-item.html`};
        document.getElementById("logo").onclick=function () {window.location=`http://${ip}:8080/index.html`};
    }catch (e){
        document.getElementById("logo").onclick=function () {window.location=`http://${ip}:8080/main.html`};
        document.getElementById("login").onclick=function () {window.location=`http://${ip}:8080/login.html`};
    }
    page = parseInt(page) - 1;
    paginationButtons(page);
    // var url = "http://localhost:8080/market/"+sessionStorage.getItem("sortAs")+"?page=" + page;
    var url = `http://${ip}:8080/market/`+sessionStorage.getItem("sortAs")+"?page=" +page;
    var response = await fetch(url, { method: "GET" });
    const listings = await response.json();
    document.getElementById("numberOfListings").textContent=listings.numberOfListings+" items in total";
    for (var i = 1; i <= 6; i++) {
        if (listings.listingCollection.length < i) {
            celarListing(i);
        } else {
            loadListing(listings.listingCollection[i - 1], i);
        }
    }
}

async function paginationButtons(page) {
    var previous = document.getElementById("previous");
    var next = document.getElementById("next");
    if (page == 0) {
        previous.className = "disabled";
    } else {
        previous.className = "";
    }

    // var url = "http://localhost:8080/market?page=" + (page + 1);
    var url = `http://${ip}:8080/market?page=` + (page + 1);
    console.log(url);
    var response = await fetch(url, { method: "GET" });
    const listings = await response.json();
    console.log(listings);
    console.log(listings.listingCollection.length);
    if (listings.listingCollection.length == 0) {
        next.className = "disabled";
    } else {
        next.className = "";
    }

}

function loadListing(listing, index) {
    var image = document.getElementById("image" + index);
    image.src = listing.photoUrl;

    var name = document.getElementById("name" + index);
    name.textContent = listing.name;

    var price = document.getElementById("price" + index);
    price.textContent = listing.price + "$";

    var listingDiv = document.getElementById("listing" + index);
    listingDiv.onclick = function () {
        openItemPanel(listing);
    }
}

async function sort() {
    sessionStorage.setItem("sortAs",document.getElementById("sort").value);
    loadPage();
}

function celarListing(index) {
    var image = document.getElementById("image" + index);
    image.src = "https://i.pinimg.com/originals/84/2a/d6/842ad68b315b0f586c30b465221da609.jpg";

    var name = document.getElementById("name" + index);
    name.textContent = "";

    var price = document.getElementById("price" + index);
    price.textContent = "";
}

function openItemPanel(listing) {
    sessionStorage.setItem('item', JSON.stringify(listing));
    console.log(listing);
    // window.location.href = "http://localhost:8080/item.html";
    window.location.href = `http://${ip}:8080/item.html`;
}

function goToNextPage() {
    var page = document.getElementById("pageNum").textContent;
    page = parseInt(page) + 1;
    document.getElementById("pageNum").textContent = page;
    loadPage();
}
function goToPreviousPage() {
    var page = document.getElementById("pageNum").textContent;
    page = parseInt(page) - 1;
    document.getElementById("pageNum").textContent = page;
    loadPage();
}