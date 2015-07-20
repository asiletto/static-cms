	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			<a class="navbar-brand" href="${site.navbar.brand.href}">${site.navbar.brand.label}</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
<#list site.navbar.menu as item>
	<#if item.href??>
					<li<#if item.selected> class="active"</#if>><a href="${item.href}">${item.label}</a></li>
	<#else>
					<li class="dropdown<#if item.selected> active</#if>">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">${item.label} <b class="caret"></b></a>
						<ul class="dropdown-menu">
		<#list item.childrens as child>
							<li<#if child.selected> class="active"</#if>><a href="${child.href}">${child.label}</a></li>
		</#list>
						</ul>
                    </li>
	</#if> 
</#list>
				</ul>
			</div>
		</div>
	</nav>
