function Filtering(){
	let element = document.getElementById('container');
	element.style.display='none';
	}
	
    function handleRadioChange() {
      let option1Radio = document.getElementById('option1');
      let option2Radio = document.getElementById('option2');
      let text = document.getElementById('text');
      let text1 = document.getElementById('text1');
      let text2 = document.getElementById('text2');

      if (option1Radio.checked) {
    	  
    	  window.location.href = "GestioneOrdine?predicate=data&testo="+text1.value+"&testo1="+text2.value;
    	  
      } else if (option2Radio.checked) {
    	  
    	  window.location.href = "GestioneOrdine?predicate=cliente&testo="+text.value;
      }
    }
    
    function mostra(){
    	let option1Radio = document.getElementById('option1');
        let option2Radio = document.getElementById('option2');
        let heading = document.getElementById('data');
        let heading2 = document.getElementById('cliente');
    	 if (option1Radio.checked) {

    		 heading2.style.display = 'none';
    		 heading.style.display = 'block';
       	  
         } else if (option2Radio.checked) {
        	 heading.style.display = 'none';
        	 heading2.style.display = 'block';
    		 
         }
    }