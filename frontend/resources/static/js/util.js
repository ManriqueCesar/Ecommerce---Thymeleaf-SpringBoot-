//View utils

$(document).on('show.bs.modal', '.modal', function () {
    var zIndex = 1040 + (10 * $('.modal:visible').length);
    $(this).css('z-index', zIndex);
    setTimeout(function () {
        $('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
    }, 0);
});

$(document).on('hidden.bs.modal', '.modal', function () {
    $('.modal:visible').length && $(document.body).addClass('modal-open');
});

function mostrarHelpBlock(idInput, tipo, msg, show) {

    let helpBlock = $(idInput).siblings(".help-block:first");
    $(idInput).removeClass("input-success");
    helpBlock.removeClass("help-block-success");
    $(idInput).removeClass("input-error");
    helpBlock.removeClass("help-block-error");
    if (show) {
        switch (tipo) {
            case "EXITO":
                $(idInput).addClass("input-success");
                helpBlock.addClass("help-block-success");
                break;
            case "ERROR":
                $(idInput).addClass("input-error");
                helpBlock.addClass("help-block-error");
                break;
        }
        helpBlock.text(msg);
        helpBlock.show();
    } else {
        helpBlock.empty();
        helpBlock.hide();
    }
}

function mostrarHelpBlockFecha(idInput, tipo, msg, show) {
    let helpBlock = $(idInput).parent().siblings(".help-block:first");
    $(idInput).removeClass("input-success");
    helpBlock.removeClass("help-block-success");
    $(idInput).removeClass("input-error");
    helpBlock.removeClass("help-block-error");
    if (show) {
        switch (tipo) {
            case "ERROR":
                $(idInput).addClass("input-error");
                helpBlock.addClass("help-block-error");
                break;
        }
        helpBlock.text(msg);
        helpBlock.show();
    } else {
        helpBlock.empty();
        helpBlock.hide();
    }
}
function mostrarHelpBlockRadio(idInput, tipo, msg, show) {
    let helpBlock = $(idInput).children(".help-block");
    helpBlock.removeClass("help-block-success");
    $('label', idInput).removeClass("custom-control-labelerror");
    helpBlock.removeClass("help-block-error");
    if (show) {
        switch (tipo) {
            case "ERROR":
                $('label', idInput).addClass("custom-control-labelerror");
                helpBlock.addClass("help-block-error");
                break;
        }
        helpBlock.text(msg);
        helpBlock.show();
    } else {
        helpBlock.empty();
        helpBlock.hide();
    }
}


function limpiarHelpBlocks(listOfIds) {
    listOfIds.forEach(function (idInput, i) {
        mostrarHelpBlock(idInput, "", "", false);
    });
}

function limpiarHelpBlocksRadio(listOfIds) {
    listOfIds.forEach(function (idInput, i) {
        mostrarHelpBlockRadio(idInput, "", "", false);
    });
}
function limpiarHelpBlocksFecha(listOfIds) {
    listOfIds.forEach(function (idInput, i) {
        mostrarHelpBlockFecha(idInput, "", "", false);
    });
}


/*File utils*/
function bytesToMegabytes(size) {
    return size / 1024 / 1024;
}

const toBase64 = file => new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result.split("base64,")[1]);
    reader.onerror = error => reject(error);
});

/*Format utils*/
let emailRegex = /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/;

function pad(n, width, z) {
    z = z || '0';
    n = n + '';
    return n.length >= width ? n : new Array(width - n.length + 1).join(z) + n;
}

/*Other utils*/
function setTitleFromMenuOption(idOptionMenu) {
    let folderTitle = "";
    let optionTitle = "";
    $(idOptionMenu).parents('.collapse').addClass('show');
    $(idOptionMenu).parents('.collapse').siblings().attr('aria-expanded', 'true');
    folderTitle = $(idOptionMenu).parents('.collapse').siblings().text();
    optionTitle = $("small", idOptionMenu).text();
    $('.menuNavegacionCarpeta').text(folderTitle);
    $('.menuNavegacionEnlace').text(optionTitle);
    $('.menuNavegacionEnlace').css('opacity', '1');
}

function limpiarInputs(listOfIds) {
    listOfIds.forEach(function (idInput, i) {
        if (!idInput.startsWith("#select")) {
            $(idInput).val(null);
        } else {
            $(idInput).find("option:disabled").prop("selected", true);
        }
    });
}

//Fecha Hoy
function getCurrentDate() {
    var fullDate = new Date();
    var month = fullDate.getMonth() + 1;
    var day = fullDate.getDate();
    if (month < 10) {
        month = "0" + month;
    }
    if (day < 10) {
        day = "0" + day;
    }
    var currentDate = day + "/" + month + "/" + fullDate.getFullYear();
    return currentDate;
}

function validarCampoDni(campo){
    var band = true;
    if(campo.val().trim().length < 8 || campo.val().trim().length > 12){
        band = false;
        campo.addClass('is-invalid');
    }else{
        campo.removeClass('is-invalid');
    }
    return band;
}

function validarCampoRuc(campo){
    var band = true;
    if(campo.val().trim().length != 11){
        band = false;
        campo.addClass('is-invalid');
    }else{
        campo.removeClass('is-invalid');
    }
    return band;
}

function validarCampoInput(campo){
    var band = true;
    if(campo.val().trim().length < 1){
        band = false;
        campo.addClass('is-invalid');
    }else{
        campo.removeClass('is-invalid');
    }
    return band;
}

function validarCampoSelect(campo){
    var band = true;
    if(campo.val() == 0){
        band = false;
        campo.addClass('is-invalid');
    }else{
        campo.removeClass('is-invalid');
    }
    return band;
}

function validarCampoCheck(nombre){
    var band = true;
    if($('input[name="'+nombre+'"]:checked').val() == null){
        band = false;
        $('input[name="'+nombre+'"]').addClass('is-invalid');
    }else{
        $('input[name="'+nombre+'"]').removeClass('is-invalid');
    }
    return band;
}

function validarCorreo(campo){
    var band = true;
    var caract = new RegExp(/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/);
    if (caract.test(campo.val().trim()) == false){
        campo.addClass('is-invalid');
        band = false;
    }else{
        campo.removeClass('is-invalid');
    }
    return band;
}

function bloquearPantalla(){
    $.blockUI({
        message: '<i class="fas fa-spin fa-sync text-white"></i>',
        overlayCSS: {
            backgroundColor: '#000',
            opacity: 0.5,
            cursor: 'wait'
        },
        css: {
            border: 0,
            padding: 0,
            backgroundColor: 'transparent'
        }
    });
}

$(document).on('input', '.input-number', function () { 
    this.value = this.value.replace(/[^0-9]/g,'');
});

function mayus(e) {
    e.value = e.value.toUpperCase();
}
