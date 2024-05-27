function setUp() {
    console.log("lashflsadjkf");
    loadPage();
}
async function loadPage() {
    var page=document.getElementById("pageNum").textContent;
    page=parseInt(page)-1;
    paginationButtons(page);
    var url = "http://localhost:8080/market?page=" + page;
    var response = await fetch(url, { method: "GET" });
    const listings=await response.json();
    console.log(listings.listingCollection.length);
    console.log(listings);
    for (var i = 1; i <= 6; i++) {
        if(listings.listingCollection.length<i){
            celarListing(i);
        }else{
            loadListing(listings.listingCollection[i-1],i);
        }
    }
}

async function paginationButtons(page){
    var previous=document.getElementById("previous");
    var next=document.getElementById("next");
    if(page==0){
        previous.className="disabled";
    }else{
        previous.className="";
    }

    var url = "http://localhost:8080/market?page=" + (page+1);
    console.log(url);
    var response = await fetch(url, { method: "GET" });
    const listings=await response.json();   
    console.log(listings);
    console.log(listings.listingCollection.length); 
    if(listings.listingCollection.length==0){
        next.className="disabled";
    }else{
        next.className="";
    }

}

function loadListing(listing, index){
    console.log(listing);
    var image=document.getElementById("image"+index);
    image.src=listing.photoUrl;

    var name=document.getElementById("name"+index);
    name.textContent=listing.name;

    var price=document.getElementById("price"+index);
    price.textContent=listing.price+"$";

    var listingDiv=document.getElementById("listing"+index);
    listingDiv.onclick = function () {
        openItemPanel(listing);
    }
}

function celarListing(index){
    var image=document.getElementById("image"+index);
    image.src="https://i.pinimg.com/originals/84/2a/d6/842ad68b315b0f586c30b465221da609.jpg";

    var name=document.getElementById("name"+index);
    name.textContent="";

    var price=document.getElementById("price"+index);
    price.textContent="";
}

function openItemPanel(listing){
    sessionStorage.setItem('item',JSON.stringify(listing));
    console.log(listing);
    window.location.href = "http://localhost:8080/item.html";
}

function goToNextPage(){
    var page=document.getElementById("pageNum").textContent;
    page=parseInt(page)+1;
    document.getElementById("pageNum").textContent=page;
    loadPage();
}
function goToPreviousPage(){
    var page=document.getElementById("pageNum").textContent;
    page=parseInt(page)-1;
    document.getElementById("pageNum").textContent=page;
    loadPage();
}