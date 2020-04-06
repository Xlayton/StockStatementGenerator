<!DOCTYPE html>
<html lang="en">

<head>
    <title>${user.firstName} ${user.lastName}'s Stock Statement</title>
    <style>
        html,
        body {
            margin: 0;
            padding: 0;
        }

        h1,
        h2 {
            text-align: center;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        td,
        th {
            border: 1px solid #dfdfdf;
            text-align: left;
            padding: 5px;
        }

        tr:nth-child(even) {
            background-color: #dfdfdf;
        }
    </style>
</head>

<body>
    <h1>Stock Statement</h1>
    <table>
        <tr>
            <th>For:</th>
            <td style="border-right:1px dashed #000">${user.lastName}, ${user.firstName}</td>
            <th style="border-left: none;">Account Number</th>
            <td>${user.accountNumber}</td>
        </tr>
        <tr>
            <th>SSN:</th>
            <td style="border-right:1px dashed #000">${user.ssn}</td>
            <th style="border-left: none;">Beggining Balance</th>
            <td>$${user.begginingBalance}</td>
        </tr>
        <tr>
            <th>Email:</th>
            <td style="border-right:1px dashed #000">${user.email}</td>
            <th style="border-left: none;">Ending Balance</th>
            <td>$${user.endingBalance}</td>
        </tr>
        <tr>
            <th>Phone:</th>
            <td style="border-right:1px dashed #000">${user.phone}</td>
            <th style="border-left: none;">Stock Holdings:</th>
            <td>$${user.stockHoldings}</td>
        </tr>
    </table>
    <h2>Transaction Details:</h2>
    <table>
        <tr>
            <th>Trade Type</th>
            <th>Stock Symbol</th>
            <th>Count</th>
            <th>Price per Share</th>
            <th>Total Cost/Gain</th>
        </tr>
        <#list user.stockTrades as trade>
            <tr>
                <td>${trade.type}</td>
                <td>${trade.stockSymbol}</td>
                <td>${trade.shareCount}</td>
                <td>$${trade.pricePerShare}</td>
                <td>$${trade.tradeTotal}</td>
            </tr>
        </#list>
    </table>
</body>

</html>