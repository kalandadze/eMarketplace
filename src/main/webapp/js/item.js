function setup() {
    var item = JSON.parse(sessionStorage.getItem('item'));
    console.log(item);
    

    var image = document.getElementById("image");
    image.src=item.photoUrl;
    
    var user = document.getElementById("email");
    user.textContent=item.user.email;

    var name = document.getElementById("name");
    name.textContent=item.name;

    var price = document.getElementById("price");
    price.textContent=item.price+"$";

    var date = document.getElementById("date");
    date.textContent = item.submissionTime.substring(0,10);

    var description = document.getElementById("description");
    description.textContent=item.description;
}