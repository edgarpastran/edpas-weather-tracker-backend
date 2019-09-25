# Weather Tracker

Sarah is interested in tracking weather conditions in her garden. To accomplish this, she has set up some weather instruments connected to a Raspberry Pi that makes HTTP calls from time to time with various metrics.
Assumptions

Because the server and the Raspberry Pi are on Sarah's home network, assume that the callers are authorized implicitly; you don't need to worry about users, passwords, or authentication of any kind.

When your server starts, it must have a clean state with no measurements in it. All data it records must reside solely in your server's process memory and will be lost when it is terminated.

HTTP bindings and models have been implemented for you. No additional changes to them are necessary, though you are free to make them.
Metrics

As time goes on, Sarah will buy new instruments to plug into her Raspberry Pi. Furthermore, some of the instruments she has already installed sometimes malfunction and stop reporting metrics! To handle this, Sarah has programmed her Raspberry Pi to be very fault-tolerant and send a measurement whether or not a given instrument has reported a metric. Her code will always report the time accurately and at proper intervals, but the other metrics may not always be reported.

The instruments plugged into the Raspberry Pi will always report their metrics as floating-point numbers. This includes instruments that have not been plugged in yet.

On day one, Sarah has installed instruments that report the following metrics. Keep in mind that she may install new ones in the future.
<table>
    <tr>
        <th>Metric Name</td>
		<th>Type</td>
		<th>Example</td>
		<th>Notes</td>
    </tr>
	<tr>
        <td>timestamp</td>
		<td>DateTime</td>
		<td>"2015-09-01T16:00:00.000Z"</td>
		<td>Always sent as an ISO-8061 string in UTC</td>
    </tr>
	<tr>
        <td>temperature</td>
		<td>float</td>
		<td>22.4</td>
		<td>in ° C</td>
    </tr>
	<tr>
        <td>dewPoint</td>
		<td>float</td>
		<td>18.6</td>
		<td>in ° C</td>
    </tr>
	<tr>
        <td>precipitation</td>
		<td>float</td>
		<td>142.2</td>
		<td>in mm</td>
    </tr>
	<tr>
        <td>...etc</td>
		<td>float</td>
		<td>1234.56</td>
		<td>Interpretation depends on instrument</td>
    </tr>
</table>

# REST API
The following is an overview of the REST endpoints your solution must expose.
<table>
    <tr>
        <th>Method</td>
		<th>Path</td>
		<th>Request Body</td>
		<th>Response Body</td>
    </tr>
	<tr>
        <td>POST</td>
		<td>/measurements</td>
		<td>Measurement</td>
		<td>(none)</td>
    </tr>
	<tr>
        <td>POST</td>
		<td>/measurements/batch</td>
		<td>Measurement[]</td>
		<td>(none)</td>
    </tr>
	<tr>
        <td>GET</td>
		<td>/measurements/:timestamp</td>
		<td>(none)</td>
		<td>Measurement</td>
    </tr>
	<tr>
        <td>GET</td>
		<td>/stats</td>
		<td>(none)</td>
		<td>Statistic[]</td>
    </tr>
</table>

The __/stats__ endpoint accepts query parameters for its request. These parameters are:
<table>
    <tr>
        <th>Parameter</td>
		<th>Indicates</td>
		<th>Notes</td>
    </tr>
	<tr>
        <td>stat</td>
		<td>which statistic to compute</td>
		<td>can be repeated for more than one statistic</td>
    </tr>
	<tr>
        <td>metric</td>
		<td>which metric to compute the statistics for</td>
		<td>can be repeated for more than one metric</td>
    </tr>
	<tr>
        <td>fromDateTime</td>
		<td>the inclusive minimum date and time of the range</td>
		<td>in UTC, ISO-8061 format</td>
    </tr>
	<tr>
        <td>toDateTime</td>
		<td>the exclusive maximum date and time of the range</td>
		<td>in UTC, ISO-8061 format</td>
    </tr>
</table>

# Acceptance Tests
The acceptance tests (ATs) found in __features/**/*.feature__.