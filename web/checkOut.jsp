<%-- 
    Document   : checkOut
    Created on : Aug 30, 2020, 12:31:00 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"> <!-- Ensures optimal rendering on mobile devices. -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge" /> <!-- Optimal Internet Explorer compatibility -->
        <title>Checkout Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%@include file="menuUser.jsp" %>
        <div class="main">
            <section class="col-main" style="min-height: 625px">
                <br/><br/><br/>
                <div id="paypal-button-container"></div>
                <form action="UpdatePayment" method="POST" name="update">
                    <input type="hidden" name="id" value="${requestScope.ID}"/>
                </form>
            </section>
            <%@include file="aside.jsp" %>
        </div>
        <%@include file="footer.jsp" %>
    </body>
    <script
        src="https://www.paypal.com/sdk/js?client-id=AZhimKHyBXc_AtJbk7GZlCv8wXNv6qIsVSLiK5MYj7IpamsYJzW5Jki2w-rWH3ADg2J9aAThgZPWzRw5"> // Required. Replace SB_CLIENT_ID with your sandbox client ID.
    </script>
    <script>
        paypal.Buttons({
            createOrder: function (data, actions) {
                // This function sets up the details of the transaction, including the amount and line item details.
                return actions.order.create({
                    purchase_units: [{
                            amount: {
                                value: '${requestScope.TOTAL}'
                            }
                        }]
                });
            },
            onApprove: function (data, actions) {
                // This function captures the funds from the transaction.
                return actions.order.capture().then(function (details) {
                    // This function shows a transaction success message to your buyer.
                    alert('Transaction completed by ' + details.payer.name.given_name);
                    document.update.submit();
                });
            }
        }).render('#paypal-button-container');
        //This function displays Smart Payment Buttons on your web page.
    </script>
</html>
