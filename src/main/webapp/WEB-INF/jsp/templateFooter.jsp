<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<script type="text/javascript">
function checkformSubscribe(theform)
{
	debugger
	var theform = document.getElementById('frmSubscribe');
	if(theform.txtSubscribe.value == "" || theform.txtSubscribe.value == " ")
	{
		debugger
		/* document.getElementById('errorMessage').innerHTML="The field is required."; */
		event.preventDefault();
	}
	else
	{
		var email = theform.txtSubscribe.value;
		document.forms[0].action = "Login.do?tabid=subscribe";
		document.getElementById('tabid').value ="subscribe";
		document.getElementById('emailId').value = email;
		document.forms[0].submit();
		document.getElementById('errorMessage').innerHTML = "<spring:message code='BzComposer.subscribe.message'/>";
			/* "Your subscription was successful! Kindly check your mailbox and confirm your subscription."+ 
		"If you don't see the email within a few minutes, check the spam/junk folder." */;
	}
}
</script>
</html>
<footer id="footer"> 
<!-- Footer / start-->
<div class="footer footer-1 bg-black">
	<div class="top-footer p-t-50 p-b-50">
		<div class="container">
			<div class="row">
				<div class="col-md-3" align="center">
					<div class="footer-block-3">
						<div class="title" style="text-align: center;">
							<h5 style="color: white;"><spring:message code="BzComposer.footer.contactsocialnetworks"/><!-- Contact Us on Social Networks --></h5>
						</div>
						<div class="social-list">
							<ul class="horizontal-list">
								<li><a><img src="${pageContext.request.contextPath}/dist/template/images/icons/social__1.png" alt="" /></a></li>
								<li><a><img src="${pageContext.request.contextPath}/dist/template/images/icons/social__2.png" alt="" /></a></li>
								<li><a><img src="${pageContext.request.contextPath}/dist/template/images/icons/social__3.png" alt="" /></a></li>
								<li><a><img src="${pageContext.request.contextPath}/dist/template/images/icons/social__4.png" alt="" /></a></li>
								<li><a><img src="${pageContext.request.contextPath}/dist/template/images/icons/social__5.png" alt="" /></a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-9" align="center">
					<div class="footer-block-2" style="margin-top: 25px">
					    <div class="input-group">
                        	<form id="frmSubscribe" onsubmit="checkformSubscribe(this.form)" action="Login.do?" method="post">
                        		<input type="hidden" id="tabid" name="tabid" value=""/>
                        		<input type="hidden" id="emailId" name="emailId" value=""/>
	                            <div class="col-md-3" >
	                            	<input type="text" id="txtSubscribe" name="txtSubscribe" class="form-control" 
	                            	placeholder="<spring:message code="BzComposer.footer.subscribeplaceholdertext"/>
	                            	<!-- Enter Your Email Id -->" style="width: 202px;float: right;" required>
	                            </div>
	                            <div class="col-md-1">
	                            	 <span class="input-group-btn">
            	              			<button class="btn btn-primary" type="submit"><spring:message code="BzComposer.footer.subscribe"/><!-- Subscribe --></button>
                	        		</span>
	                            </div>
	                            <div class="col-md-5" align="right">
	                            	<label id="errorMessage" style="color: #f00;font-size: 1em;font-weight: normal;"></label>
	                            </div>
    	                    </form>
                        </div>
					</div>
				</div>
			</div>
					<!-- <div class="col-md-6">
						<div class="footer-block-1">
							
							<div class="quick-link">
								<div class="row">
									  <div class="footer-text">
                    <h2>What Clients are Saying</h2>
                    <p>We have had difficulty in running our online business for a long time despite our heavy investments. BZcomposer created our website in 2 weeks and helped us.</p>
                </div>
								</div>
							</div>
						</div>
					</div> -->
					<!-- <div class="col-md-3">
						<div class="footer-block-2">
							<div class="title">
								<h3>Join Our Mailing List</h3>
							</div>
						<div class="input-group">
                            <input type="text" class="form-control" placeholder="">
                            <span class="input-group-btn">
                          <button class="btn btn-primary" type="button">Subscribe</button>
                        </span>
                        </div>
						</div>
					</div> -->
				
			<div class="row">
				<div>
				</div>
				<div class="col-md-3 b_footerwidgets" style="max-width: 320px;float:left;">
					<div class="textwidget" style="max-width: 320px;float: left;">
						<h4><spring:message code="BzComposer.footer.aboutus"/></h4>
						<ul class="menu">
							<li><a href="https://www.nextbits.com/about-us/"><spring:message code="BzComposer.footer.aboutnextbits"/></a></li>
							<li><a href="#"><spring:message code="BzComposer.footer.support"/>Support</a></li>
							<!-- <li><a href="/contact-us/">Contact us</a></li> -->
							<li><a href="https://www.nextbits.com/contact-us/"><spring:message code="BzComposer.footer.contactus"/></a></li>
							<li><a href="https://www.nextbits.com/page-sitemap.xml/"><spring:message code="BzComposer.footer.sitemap"/></a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-3 b_footerwidgets" style="max-width: 320px;float: left;">
					<div class="textwidget" style="max-width: 320px;float: left;">
						<h4><spring:message code="BzComposer.footer.businesssubmission"/></h4>
						<ul class="menu">
							<!-- <li><a href="/about-nextbits/">Membership</a></li> -->
							<li><a href="https://www.nextbits.com/membership-plan/"><spring:message code="BzComposer.footer.membership"/></a>
							<!-- <li><a href="#">Submit Your Business</a></li> -->
							<li><a href="https://www.nextbits.com/user-login/"><spring:message code="BzComposer.footer.submitbusiness"/></a></li>
							<!-- <li><a href="/contact-us/">Claim Your Business</a></li> -->
							<li><a href="https://www.nextbits.com/claim-your-business/"><spring:message code="BzComposer.footer.claimbusiness"/></a></li>
 						</ul>
					</div>
				</div>
				<div class="col-md-3 b_footerwidgets" style="max-width: 320px;float: left;">
					<div class="textwidget" style="max-width: 320px;float: left;">
						<h4><spring:message code="BzComposer.footer.sellingonnextbits"/></h4>
						<ul class="menu">
							<li><a href="#"><spring:message code="BzComposer.footer.howtosell"/></a></li>
							<li><a href="#"><spring:message code="BzComposer.footer.manageproducts"/></a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-3 b_footerwidgets" style="max-width: 320px;float: left;">
					<div class="textwidget" style="max-width: 320px;float: left;">
						<h4><spring:message code="BzComposer.footer.safetyandsupport"/></h4>
						<ul class="menu">
							<li><a href=" /about-nextbits/"><spring:message code="BzComposer.footer.helpcenter"/></a></li>
							<li><a href="https://www.nextbits.com/blog/"><spring:message code="BzComposer.footer.blog"/></a></li>
							<li><a href="#"><spring:message code="BzComposer.footer.privatepolicy"/></a></li>
							<li><a href="#"><spring:message code="BzComposer.footer.disclaimer"/></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="bot-footer">
		<div class="container">
			<div class="block-inner p-t-35 p-b-60">
				<div class="block-left">
					<ul>
						<li><a href="https://www.nextbits.com/business-directory/"><spring:message code="BzComposer.footer.yellowpage"/></a></li>
						<li><a href="https://www.nextbits.com/service-contractor/"><spring:message code="BzComposer.footer.servicecontractor"/></a></li>
						<li><a href="https://www.nextbits.com/community/"><spring:message code="BzComposer.footer.community"/></a></li>
						<li><a href="https://www.nextbits.com/real-estate/"><spring:message code="BzComposer.footer.realestate"/></a></li>
						<li><a href="https://www.nextbits.com/lodging/"><spring:message code="BzComposer.footer.lodging"/></a></li>
						<li><a href="https://www.nextbits.com/restaurants/"><spring:message code="BzComposer.footer.resturants"/></a></li>
						<li><a href="https://www.nextbits.com/coupons/"><spring:message code="BzComposer.footer.coupons"/></a></li>
						<li><a href="https://www.nextbits.com/coupons/blog/"><spring:message code="BzComposer.footer.blog"/></a></li>
						<li><a href="https://www.nextbits.com/job-listings/"><spring:message code="BzComposer.footer.jobs"/></a></li>
						<li><a href="https://www.nextbits.com/shop/"><spring:message code="BzComposer.footer.shopping"/></a></li> 					 				</ul>
						
						<!-- <span>© Copyright 2011 BZComposer.com - is a trademark of Nextbits Corporation</span> -->
					</div>
					<!--  <div class="block-right-copyright"> -->
						 
					
					<%-- <div class="block-right-social-links">
					
						<p>Join Social Networks:</p>
						
						<ul class="horizontal-list">
									<li>
									<a><img src="${pageContext.request.contextPath}/dist/template/images/icons/social__1.png" alt="" /></a></li>
									<li>
									<a><img src="${pageContext.request.contextPath}/dist/template/images/icons/social__2.png" alt="" /></a></li>
									<li>
									<a><img src="${pageContext.request.contextPath}/dist/template/images/icons/social__3.png" alt="" /></a></li>
									<li>
									<a><img src="${pageContext.request.contextPath}/dist/template/images/icons/social__4.png" alt="" /></a></li>
									<li>
									<a><img src="${pageContext.request.contextPath}/dist/template/images/icons/social__5.png" alt="" /></a></li>
								</ul>
					
					</div> --%>
					
				</div>
				<div align="center">
					<p style="color: white;"> <spring:message code="BzComposer.footer.footertext"/>
					<!-- Notice ©-2013 nextbits.com . All rights reserved. --></p>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer / end--> </footer>