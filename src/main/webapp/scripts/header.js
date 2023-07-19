function submitForm(page,value) {
  // Create a form element
  var form = document.createElement('form');
  form.method = 'POST'; // Set the HTTP method to POST
  form.action = page;   // Set the action URL

  // Create an attribute element
  if(value!=null){
  var attribute = document.createElement('input');
  attribute.type = 'hidden';
  attribute.name = 'OrdType';
  attribute.value = value;

  // Append the attribute to the form
  form.appendChild(attribute);
  }
  // Append the form to the document body
  document.body.appendChild(form);

  // Submit the form
  form.submit();
}