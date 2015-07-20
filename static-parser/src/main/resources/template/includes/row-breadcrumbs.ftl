        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">${page.title}
<#if subtitle??>                
                    <small>${page.subtitle}</small>
</#if>                    
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${site.navbar.brand.href}">${site.navbar.brand.label}</a>
                    </li>
                    <li class="active">${page.title}</li>
                </ol>
            </div>
        </div>