<#import "/spring.ftl" as spring/>
<#import "include/layout.ftl" as layout> 

<@layout.header "Welcome", "Welcome to TIMPS">


    <!-- Loading Bootstrap -->
    <link href="css/vendor/bootstrap.min.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="css/flat-ui.min.css" rel="stylesheet">
    
<style>

</style>

</@layout.header>

<@layout.body>

<div class="container">
      <div class="row">
      <div class="login">
        <div class="login-screen">
          <div class="login-icon">
            <h4>TIMPS</small></h4>
          </div>

          <div class="login-form">
            <div class="form-group">
              <input type="text" class="form-control login-field" value="" placeholder="Enter your name" id="login-name" />
              <label class="login-field-icon fui-user" for="login-name"></label>
            </div>

            <div class="form-group">
              <input type="password" class="form-control login-field" value="" placeholder="Password" id="login-pass" />
              <label class="login-field-icon fui-lock" for="login-pass"></label>
            </div>

            <a class="btn btn-primary btn-lg btn-block" href="#">Log in</a>
            <a class="login-link" href="#">Lost your password?</a>
          </div>
        </div>
      </div>

     </div>
    </div> <!-- /container -->
</div>

<div style="position:absolute; right:40px; bottom:20px;">CopyRight 2015, Shanghai Proton & Heavy Ion Center</div>
</@layout.body>

<@layout.footer>

    <script src="js/flat-ui.min.js"></script>

</@layout.footer>
 
