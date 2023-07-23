
  
    // Define the minimum and maximum price values as integers
    const minPrice = 0;
    const maxPrice = 500;

    // Initialize the slider
    const slider = document.getElementById('price-range-slider');
    noUiSlider.create(slider, {
      start: [minPrice, maxPrice],
      connect: true,
      range: {
        'min': minPrice,
        'max': maxPrice
      },
      format: {
        to: value => Math.round(value), // Convert to integer
        from: value => Math.round(value) // Convert back to float if needed
      }
    });

    // Get the price range elements
    const priceRange = document.getElementById('price-range');
	let minValue = minPrice;
	let maxValue = maxPrice;
    // Update the selected price range when the slider values change
    slider.noUiSlider.on('update', function (values) {
      minValue = parseInt(values[0]);
      maxValue = parseInt(values[1]);

      priceRange.textContent = `$${minValue} - $${maxValue}`;
    });
    
    
 function filterFormSubmit() {
 
  let form = document.getElementById("filterForm");

  // Set the variables as form parameters
  let input1 = document.createElement("input");
  input1.type = "hidden";
  input1.name = "min";
  input1.value = minValue;
  form.appendChild(input1);

  let input2 = document.createElement("input");
  input2.type = "hidden";
  input2.name = "max";
  input2.value = maxValue;
  form.appendChild(input2);
  
   // Submit the form
  form.submit();
}
    
