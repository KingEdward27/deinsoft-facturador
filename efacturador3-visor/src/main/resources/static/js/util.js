$(document).ready(function () {
    var a = $('<!--[if IE]><link rel="shortcut Icon"  type="image/X-icon" href="../../images/favicon.png" /><![endif]--><![if !IE]><link rel="shortcut Icon" type="image/png"  href="../../images/favicon.png" /><![endif]>');
    $("head").append(a);
    $('#loading').hide();
    $.ajaxSetup({cache: false});
});
$(document).ajaxStart(function () {
    $("#loading").show();
    //$("#loading").show();// show the gif image when ajax starts
}).ajaxStop(function () {
    $("#loading").hide();
});
//$(document).ajaxStart(function () {
//    $(document).ajaxSend(function(evt, request, settings) {
//        ajaxLoader("0");
//     });
//    //$("#loading").show();// show the gif image when ajax starts
//}).ajaxStop(function () {
//    ajaxLoader("hide"); // hide the gif image when ajax completes
//});
//function ajaxLoader(action) {
//    if (action === "hide") {
//        $('body').oLoader(action);
//    } else {
//        $('body').oLoader({
//            image: '../images/loader.gif',
//            wholeWindow: true,
//            lockOverflow: true,
//            effect: 'explode',
//            backgroundColor: '#000',
//            fadeInTime: 1000,
////            style: "<div style='position:absolute;right:10px;bottom:10px;background:#000;color:#fff;padding:5px;border-radius:4px'>Cargando...</div>",
//            
//            fadeLevel: 0.4
//        });
//    }
//}