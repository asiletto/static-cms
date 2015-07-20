    <script src="${site.jqueryJs}"></script>
    <script src="${site.bootstrapJs}"></script>
<#if page.includeCarousel>
    <script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>
</#if>