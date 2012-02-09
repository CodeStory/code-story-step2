function replaceWith(selector, url) {
    $.getJSON(url, function (json) {
        $(selector).replaceWith(Mustache.to_html($(selector).html(), json));
    });
}
