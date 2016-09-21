$(document).ready(function(){

    $(document).on("click",".save-btn",function(event){
        $.post( "servlet/SaveBookServlet", { id: this.id})
            .done(function( data ) {
                if(data == "success")
                    alert( "Save the file " + data );
                else
                    alert("something try later");
            });
    });

    $(document).on("click",".share-btn",function(event){
        $.post( "servlet/ShareBookServlet", { id: this.id})
            .done(function( data ) {
                if(data == "success")
                    alert( "Great, Thanks for share: " + data );
                else
                    alert("something try later");
            });
    });

    $( "#file" ).change(function() {
        $("#fileName").val(getFileName($("#file").val()));
    });

    function getFileName(path) {
        var arr = path.split("\\");
        return arr[arr.length-1];
    }

    $(document).on("click",'.getPrintPanel',function(event) {
        $.post("servlet/PrintBookServlet", {
            id: this.id,
            dataType:'json'
        }).done(function(data) {
            var obj = jQuery.parseJSON(data);
            var html = "";
            $.each(obj, function(i, item) {
                if(item.isPrinted == 0) {
                    html += "<tr><td><a href='"+ item.phyPath +"'>"+item.bookName+"</a></td>" +
                    "<td><button class='print-btn' id='"+item.bookName+"' type='button' value='print'>print</button></td></tr>";
                } else {
                    html += "<tr><td><a href='"+ item.phyPath +"'>"+item.bookName+"</a></td>" +
                    "<td><button class='print-btn' id='"+item.bookName+"' type='button' value='printed'>printed</button></td></tr>";
                }
            });
            html = '<tbody id="replaceHere">' + html + '</tbody>'
            $('#replaceHere').replaceWith(html);
        });
    });



    $(document).on("click",".print-btn",function(event){
        window.print();
        $.post("servlet/PrintedServlet", {
            bookName: this.id
        }).done(function(data) {
            if (data == 'success') {
                $(this).text("printed");
            } else {
                alert("error occurred! try later");
            }
        });
    });

    var dataSet = new Array();
    $.post("servlet/GetAllBooksServlet", {
    }).done(function(data) {
        var obj = jQuery.parseJSON(data);
        $.each(obj, function(i, item) {
            dataSet[i] = [item.bookName, item.major ,item.professor ,item.courseLevel ,item.sharedTimes ,item.htmlButton];
        });
        $('#mainTable').DataTable( {
            data: dataSet,
            columns: [
                { title: "BookName" },
                { title: "Major" },
                { title: "Professor" },
                { title: "Course Level" },
                { title: "Share Times" },
                { title: "Save" }
            ]
        } );
    });

});

