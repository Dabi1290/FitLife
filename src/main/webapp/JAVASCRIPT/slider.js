
  
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

    // Update the selected price range when the slider values change
    slider.noUiSlider.on('update', function (values) {
      const minValue = parseInt(values[0]);
      const maxValue = parseInt(values[1]);

      priceRange.textContent = `$${minValue} - $${maxValue}`;
    });
