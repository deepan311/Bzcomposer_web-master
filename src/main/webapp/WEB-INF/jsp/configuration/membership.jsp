<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page errorPage="/include/sessionExpired.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="/WEB-INF/jsp/include/headlogo.jsp" />
<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
<jsp:include page="/WEB-INF/jsp/include/menu.jsp" />
<title><spring:message code="BzComposer.devicemanagertitle" /></title>
<link href="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui-tab.css" rel="stylesheet" media="screen" />
<script src="${pageContext.request.contextPath}/tableStyle/tab/jquery-ui.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> -->
<script type="text/javascript">
function toggleFunction() {
  var x = document.getElementById("divtoggle");
  var lftmenu = document.getElementById("leftMenu");
  if (x.style.display === "none") {
    x.style.display = "block";
    lftmenu.style.width = "180px";
    lftmenu.style.position = "relative";
  } else {
    x.style.display = "none";
    lftmenu.style.width = "0";
    lftmenu.style.position = "absolute";
  }
} 
$(function() {
    $("#tabs").tabs();
});

function subscribePlan(plan){
    window.location = "/paypal-payment-process/"+plan;
}
</script>

<style type="text/css">
.plan-amount-details{
  height: 100px;background-color: #337ab7;padding-top: 30px;
}
.plan-amount-details h2{
    color: #fff !important;
}
.plan-details{
  display: flex;flex-direction: column;width: 100%;align-items: center;
}
.items-listing-info{
  height: 150px;
}
.items-listing-info li{
  line-height: 2.0em !important;
}
.paypal-btn{
  background-color: unset !important;
  background-image: url('https://www.tipsandtricks-hq.com/wp-content/uploads/2021/07/paypal-subscibe.png') !important;
  width: 200px !important;
  height: 75px !important;
  box-shadow: unset !important;
  cursor: pointer;
  border: 0;
}
img {
    height: auto;
    max-width: 100%;
    display: block;
}
</style>
</head>
<body>
	<div id="ddcolortabsline">&nbsp;</div>
	<div id="cos">
	<div class="statusquo ok">
	<div id="hoja">
	<div id="blanquito">
	<div id="padding">
	<div>
		<span style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
			<spring:message code="BzComposer.configuration.configurationtitle"/>
		</span></div>
	<div>
	<div id="table-negotiations" style="padding: 0; border: 1px solid #ccc;">
	<table cellspacing="0"  style="border: 0;width: 100%;overflow-y:scroll;" class="section-border">
		<span style="font-size:30px;cursor:pointer; margin-left: 20px;" onclick="toggleFunction()">&#9776;</span>
		<tr>
			<td id="leftMenu" style="position: relative; width: 180px; vertical-align: top;">
			<table>
				<tr><td><jsp:include page="menuPage.jsp" /></td></tr>
			</table>
			</td>
			<td valign="top" >
				<div id="tabs" style="height:auto;">
					<ul>
 						<li style="font-size: 12px;">
 							<a href="#membershipPlanTab"><spring:message code="BzComposer.membership.myPlan"/></a>
						</li>
 						<li style="font-size: 12px;">
 							<a href="#membershipPlansTab"><spring:message code="BzComposer.membership.membershipPlans"/></a>
						</li>
					</ul>
				<!-- My Plan Starts -->
			 <div id="membershipPlanTab" style="display:none;">
				<table class="table-notifications" width="100%">
                    <tr>
                        <th align="left" colspan="2" style="font-size: 12px; padding: 5px;">
                            <spring:message code="BzComposer.membership.myMembershipPlan"/>
                        </th>
                    </tr>
                    <tr>
                        <td style="font-size: 14px;color:green;">
                            <b><spring:message code="BzComposer.membership.youAreEnterpriseMember"/></b>
                        </td>
                        <td style="font-size: 14px;text-align: right;color:green;">
                            <b><spring:message code="BzComposer.global.expdate"/>: 14/08/2021</b>
                        </td>
                    </tr>
                    <tr>
                        <td align="center" colspan="2" style="font-size: 12px; padding: 20px;">
                            <h2><spring:message code="BzComposer.membership.enterpriseBenefits"/></h2>
                        </td>
                    </tr>
                    <tr>
                        <td align="center" colspan="2" style="font-size: 12px; padding: 5px;">
                            <div class="row text-center container">
                                <div class="col-sm-4 mb-3" style="font-size: 16px;">
                                    <p>
                                        <img src="${pageContext.request.contextPath}/images/new/planA1.png" alt="image">
                                    </p>
                                    <h5>Upload Unlimited images for business</h5>
                                    <p>As a Gold member, you can upload Unlimited images for business.If you need to upload more images for business then updrade your account.</p>
                                </div>
                                <div class="col-sm-4 mb-3" style="font-size: 16px;">
                                    <p>
                                        <img src="${pageContext.request.contextPath}/images/new/planA2.png" alt="image">
                                    </p>
                                    <h5>Unlimited local connections</h5>
                                    <p>As a Gold member, you get Unlimited Local Connections! Connect anytime with all the businesses in Buena Park and a 100-miles radius.</p>
                                </div>
                                <div class="col-sm-4 mb-3" style="font-size: 16px;">
                                    <p>
                                        <img src="${pageContext.request.contextPath}/images/new/planA3.png" alt="image">
                                    </p>
                                    <h5>Create Own Referral Card</h5>
                                    <p>You will create your own referral card and send to anyone.So, please create your refferal card and enhance your business.</p>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="center" colspan="2" style="font-size: 12px; padding: 5px;">
                            <div class="row text-center container">
                                <div class="col-sm-4 mb-3" style="font-size: 16px;">
                                    <p>
                                        <img src="${pageContext.request.contextPath}/images/new/planA4.png" alt="image">
                                    </p>
                                    <h5>Join Community</h5>
                                    <p>As a member with us, you can join any community and create topics or send comments for other topics.</p>
                                </div>
                                <div class="col-sm-4 mb-3" style="font-size: 16px;">
                                    <p>
                                        <img src="${pageContext.request.contextPath}/images/new/planA5.jpeg" alt="image">
                                    </p>
                                    <h5>Send Other Business's Referral Card</h5>
                                    <p>You can send other business's referral card to any one or own with email address.</p>
                                </div>
                                <div class="col-sm-4 mb-3" style="font-size: 16px;">
                                    <p>
                                        <img src="${pageContext.request.contextPath}/images/new/planA6.png" alt="image">
                                    </p>
                                    <h5>Support</h5>
                                    <p>Our team of professional experts are always available 24/7 to answer all your queries. You can use the contact form OR Chat with us using support menu of My Business below to send us your valuable feedback.</p>
                                </div>
                            </div>
                        </td>
                    </tr>
				</table>
			</div> 
			<!-- My Plan Ends -->
			<!-- Membership Plans Starts -->
			<div id="membershipPlansTab" style="display: none;">
				<div class="membership-header">
                  <div class="row text-center">
                    <div class="col-12">
                      <h2><spring:message code="BzComposer.membership.chooseYourPricing"/></h2>
                      <p><spring:message code="BzComposer.membership.chooseYourPricingMsg"/></p>
                    </div>
                  </div>
                </div>
                <div class="membership-content">
                    <div class="row text-center">
                        <div class="col-sm-3 mb-3">
                            <div class="card">
                                <div class="plan-amount-details">
                                  <h2>$0.00<span style="color: #fff !important;font-weight: 100 !important;">/mo</span></h2>
                                </div>
                                <div class="plan-details text-center">
                                  <h3 style="color: #337ab7;font-size: 1.3rem;"><spring:message code="BzComposer.membership.freeTrial"/></h3>
                                  <ul class="items-listing-info">
                                    <li><spring:message code="BzComposer.membership.for5Listing"/></li>
                                    <li><spring:message code="BzComposer.membership.allowUpto5Listings"/></li>
                                    <li><spring:message code="BzComposer.membership.allowUpto5Images"/></li>
                                  </ul>
                                  <div class="pricing-btn" disabled><button type="button" class="paypal-btn"></button></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="card">
                                <div class="plan-amount-details">
                                  <h2>$9.95<span style="color: #fff !important;font-weight: 100 !important;">/mo</span></h2>
                                </div>
                                <div class="plan-details text-center">
                                  <h3 style="color: #337ab7;font-size: 1.3rem;"><spring:message code="BzComposer.membership.standard"/></h3>
                                  <ul class="items-listing-info">
                                    <li><spring:message code="BzComposer.membership.for10Listing"/></li>
                                    <li><spring:message code="BzComposer.membership.allowUpto10Listings"/></li>
                                    <li><spring:message code="BzComposer.membership.allowUpto10Images"/></li>
                                  </ul>
                                  <div class="pricing-btn">
                                    <button type="button" class="paypal-btn" onclick="subscribePlan('standard');"></button>
                                  </div>
                                </div>
                            </div>
                        </div>
                  <div class="col-sm-3">
                    <div class="card">
                        <div class="plan-amount-details">
                          <h2>$39.95<span style="color: #fff !important;font-weight: 100 !important;">/mo</span></h2>
                        </div>
                        <div class="plan-details text-center">
                          <h3 style="color: #337ab7;font-size: 1.3rem;"><spring:message code="BzComposer.membership.professional"/></h3>
                          <ul class="items-listing-info">
                            <li><spring:message code="BzComposer.membership.for100Listing"/></li>
                            <li><spring:message code="BzComposer.membership.allowUpto100Listings"/></li>
                            <li><spring:message code="BzComposer.membership.allowUpto100Images"/></li>
                          </ul>
                          <div class="pricing-btn">
                            <button type="button" class="paypal-btn" onclick="subscribePlan('professional');"></button>
                          </div>
                        </div>
                    </div>
                  </div>
                  <div class="col-sm-3">
                    <div class="card">
                        <div class="plan-amount-details">
                          <h2>$89.00<span style="color: #fff !important;font-weight: 100 !important;">/mo</span></h2>
                        </div>
                        <div class="plan-details text-center">
                          <h3 style="color: #337ab7;font-size: 1.3rem;"><spring:message code="BzComposer.membership.enterprise"/></h3>
                          <ul class="items-listing-info">
                            <li><spring:message code="BzComposer.membership.forUnlimitedListing"/></li>
                            <li><spring:message code="BzComposer.membership.allowUptoUnlimitedListings"/></li>
                            <li><spring:message code="BzComposer.membership.allowUptoUnlimitedImages"/></li>
                          </ul>
                          <div class="pricing-btn">
                            <button type="button" class="paypal-btn" onclick="subscribePlan('enterprise');"></button>
                          </div>
                        </div>
                    </div>
                  </div>

                    </div>
                </div>
			</div>
			<!-- Membership Plans Ends -->
			</div>
			</td>
		</tr>
	</table>
	<div>
	</div>
	<div><input type="hidden" name="tabid" id="tid" value="" />
	</div>
	</div>
	<div>
	<div align="center">
		<input type="button" class="bottomButton formButton" value='<spring:message code="BzComposer.global.save"/>' />
		<input type="reset" class="bottomButton formButton" name="Cancel" value="<spring:message code="BzComposer.global.cancel"/>"/>
		</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
</body>
</html>