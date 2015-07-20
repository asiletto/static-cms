<!DOCTYPE html>
<html lang="en">

<head>

<#include "head.ftl">

</head>

<body>

	<#include "navbar.ftl">

	<#if page.includeCarousel>
		<#include "carousel.ftl">
	</#if>

    <div class="container">

		<#include "row-marketing.ftl">
		<#include "row-portfolio.ftl">
		<#include "row-features.ftl">
		<#include "well.ftl">

	    <hr>
	
		<#include "footer.ftl">

    </div>
    
<#include "footerScript.ftl">

</body>

</html>
