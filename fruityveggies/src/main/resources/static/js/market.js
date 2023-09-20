/**
 * 
 */
 document.addEventListener('DOMContentLoaded', ()=> {
	
	console.log('market js in');

    var viewButtons = document.querySelectorAll(".view-button");
    
    viewButtons.forEach(function(button) {
        
        button.addEventListener("click", function() {
            var itemId = button.getAttribute("data-id");
            var url = "/freshmarket/freshmarket?id=" + itemId;
            window.location.href = url;
        });
        
    });
 });