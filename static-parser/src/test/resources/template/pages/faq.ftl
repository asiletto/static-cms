<!DOCTYPE html>
<html lang="en">

<head>

<#include "head.ftl">

</head>

<body>

	<#include "navbar.ftl">

	<#if includeCarousel>
		<#include "carousel.ftl">
	</#if>

    <div class="container">

		<#include "row-breadcrumbs.ftl">
		<#include "block-faq.ftl">
		
	    <hr>
	
		<#include "footer.ftl">

    </div>
    
<#include "footerScript.ftl">

</body>

</html>
