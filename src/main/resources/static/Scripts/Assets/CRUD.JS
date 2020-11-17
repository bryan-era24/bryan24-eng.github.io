$(document).ready(function() {
    $('[data-toggle="tooltip"]').tooltip();
 
    const checkbox = $('table tbody input[type="checkbox"]');
    const select = $("#selectAll");

    select.click(function() {
        if(this.checked) {
            checkbox.each(function () {
                this.checked = true;                        
            });
        } else {
            checkbox.each(function () {
                this.checked = false;                        
            });
        }
    });
    
    checkbox.click(function() {
        if(!this.checked) {
            select.prop("checked", false);
        }
    });
});

