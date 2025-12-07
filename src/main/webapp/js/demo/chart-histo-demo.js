// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

function number_format(number, decimals, dec_point, thousands_sep) {
	number = (number + '').replace(',', '').replace(' ', '');
	var n = !isFinite(+number) ? 0 : +number,
		prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
		sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
		dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
		s = '',
		toFixedFix = function(n, prec) {
			var k = Math.pow(10, prec);
			return '' + Math.round(n * k) / k;
		};
	s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
	if (s[0].length > 3) {
		s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
	}
	if ((s[1] || '').length < prec) {
		s[1] = s[1] || '';
		s[1] += new Array(prec - s[1].length + 1).join('0');
	}
	return s.join(dec);
}

// Bar Chart Example
var ctx = document.getElementById("myHistoChart");
var dataValues = JSON.parse(ctx.getAttribute("data-values"));

console.log(dataValues);
var myBarChart = new Chart(ctx, {
	type: 'bar',
	data: {
		labels: ["Deposit", "Withdraw", "Loan EMI"], // 5 bars
		datasets: [{
			label: "Amount",
			backgroundColor: "#4e73df", // Bar color
			hoverBackgroundColor: "#2e59d9", // Hover color
			borderColor: "#4e73df", // Border color
			data: dataValues, // Static data for 5 bars
		}],
	},
	options: {
		maintainAspectRatio: false,
		layout: {
			padding: {
				left: 10,
				right: 25,
				top: 25,
				bottom: 0
			}
		},
		scales: {
			x: {
				grid: {
					display: false,
					drawBorder: false
				},
				ticks: {
					maxTicksLimit: 5
				},
				maxBarThickness: 25,
			},
			y: {
				ticks: {
					min: 0,
					max: 3000,
					maxTicksLimit: 6,
					padding: 10,
					callback: function(value, index, values) {
						return 'Rs. ' + number_format(value);
					}
				},
				grid: {
					color: "rgb(234, 236, 244)",
					zeroLineColor: "rgb(234, 236, 244)",
					drawBorder: false,
					borderDash: [2],
					zeroLineBorderDash: [2]
				}
			},
		},
		plugins: {
			legend: {
				display: false
			},
			tooltip: {
				titleMarginBottom: 10,
				titleFont: {
					family: 'Nunito',
					size: 14,
					weight: 'bold'
				},
				backgroundColor: "rgb(255,255,255)",
				bodyFont: {
					family: 'Nunito',
					size: 12
				},
				borderColor: '#dddfeb',
				borderWidth: 1,
				padding: 15,
				callbacks: {
					label: function(tooltipItem) {
						return 'SAmount: Rs. ' + number_format(tooltipItem.raw);
					}
				}
			}
		}
	}
});