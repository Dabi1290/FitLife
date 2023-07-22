function Filtering(){
	var element = document.getElementById('container');
	element.style.display='none';
	}
	
    function handleRadioChange() {
      var option1Radio = document.getElementById('option1');
      var option2Radio = document.getElementById('option2');
      var text = document.getElementById('text');
      var text1 = document.getElementById('text1');
      var text2 = document.getElementById('text2');

      if (option1Radio.checked) {
    	  
    	  window.location.href = "GestioneOrdine?predicate=data&testo="+text1.value+"&testo1="+text2.value;
    	  
      } else if (option2Radio.checked) {
    	  
    	  window.location.href = "GestioneOrdine?predicate=cliente&testo="+text.value;
      }
    }
    
    function mostra(){
    	var option1Radio = document.getElementById('option1');
        var option2Radio = document.getElementById('option2');
        var heading = document.getElementById('data');
        var heading2 = document.getElementById('cliente');
    	 if (option1Radio.checked) {

    		 heading2.style.display = 'none';
    		 heading.style.display = 'block';
       	  
         } else if (option2Radio.checked) {
        	 heading.style.display = 'none';
        	 heading2.style.display = 'block';
    		 
         }
    }