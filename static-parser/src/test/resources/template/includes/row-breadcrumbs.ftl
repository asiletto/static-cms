        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">${title}
<#if subtitle??>                
                    <small>${subtitle}</small>
</#if>                    
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${navbar.brand.href}">${navbar.brand.label}</a>
                    </li>
                    <li class="active">${title}</li>
                </ol>
            </div>
        </div>