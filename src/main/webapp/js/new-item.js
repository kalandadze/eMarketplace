async function addListing() {
  const nameInput = document.getElementById('name');
  const priceInput = document.getElementById('price');
  const photoInput = document.getElementById('photo');
  const descriptionInput = document.getElementById('description');

  if (!nameInput.value || !priceInput.value || !photoInput.files[0] || !descriptionInput.value) {
    alert('Please fill in all required fields.');
    return;
  }

  const formData = new FormData();
  formData.append('name', nameInput.value);
  formData.append('price', priceInput.value);
  formData.append('photo', photoInput.files[0]);
  formData.append('description', descriptionInput.value);

  const headers = new Headers({
    'Authorization': sessionStorage.getItem("token"),
  });
  const response = await fetch('http://localhost:8080/market', {
    method: 'POST',
    headers,
    body: formData,
  });

  if (response.ok) {
    window.location = 'http://localhost:8080/index.html'
  } else {
    alert('Error submitting form:', await response.text());
  }
}