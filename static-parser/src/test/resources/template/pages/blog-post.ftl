<!DOCTYPE html>
<html lang="en">

<head>

<#include "head.ftl">

</head>

<body>

	<#include "navbar.ftl">

    <div class="container">

		<#include "row-breadcrumbs.ftl">

		<div class="row">
            <div class="col-lg-8">
				<#include "block-blog-post.ftl">
				<#include "block-blog-comments.ftl">
            </div>


            <div class="col-md-4">
				<#include "block-blog-search.ftl">
				<#include "block-blog-categories.ftl">
				<#include "block-widget.ftl">

            </div>

        </div>
            
	    <hr>
	
		<#include "footer.ftl">

    </div>
    
<#include "footerScript.ftl">

</body>

</html>
