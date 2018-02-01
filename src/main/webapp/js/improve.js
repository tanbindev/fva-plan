/**
 * Created by PC on 2018/1/22.
 */
$(document).ready(function() {
    $(".ok").click(function(){
        var win = parseInt($("#example1").val($("#example1").width())[0].value);
        if(win > 1000) {
            $("#example1").removeClass('hantable1');
            $("#example1").addClass("hantable1s");
        } else {
            $("#example1").removeClass('hantable1s');
            $("#example1").addClass("hantable1");
        }
        $(".lnoOK").toggle(1000);
        $("#text-lno").toggle(1000);
        $("#example2").toggle(1000);
    });
});