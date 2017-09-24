# WebProgrammingClient-side

Problem Statement: 
------------------
A Currency broker company has asked us to build a system that notifies them when a currency pair rate reaches the target rate. User configures the currency pair and target rate.

The URL to get the Rates in XML form: "http://rates.fxcm.com/RatesXML"

Example of the rate: 
&lt;Rate Symbol="EURUSD"&gt;
&lt;Bid&gt;1.38022&lt;/Bid&gt;
&lt;Ask&gt;1.38042&lt;/Ask&gt;
&lt;High&gt;1.38266&lt;/High&gt;
&lt;Low&gt;1.37634&lt;/Low&gt;
&lt;Direction&gt;0&lt;/Direction&gt;
&lt;Last&gt;10:42:43&lt;/Last&gt;
&lt;/Rate&gt;

Here "Bid" is the current rate. "Ask" is the suggested sell rate. "High" is day high, "Low" is day low, "Direction" is 1=&gt;up, -1=&gt;down, 0=&gt;no change compared to last rate, and "Last" is the time for last tick.

Design considerations:
----------------------
1. You will need a parser to parse the XML. There are several examples online.
2. Start with hard coded user configuration. For example. Pair: EURUSD, Target rate: 1.381 
3. Make it user entry once you have working code.
4. You will periodically check the rates, consider using a schedular.
4. For notification, we will assume that there is a system that will be called to notify users. You only display it on the screen if target has been reached.

Deliverables:
--------------
1. Software design document (SDD) that includes requirements, analysis, architect diagram (multi tier architecture), class diagram, and test cases. (40)
2. Working code. You can include the code in SDD if that's what you prefer. (40)
