/*
 * creates a new XMLHttpRequest object which is the backbone of AJAX,
 * or returns false if the browser doesn't support it
 */
function getXMLHttpRequest() {
	var xmlHttpReq = false;
	// to create XMLHttpRequest object in non-Microsoft browsers
	if (window.XMLHttpRequest) {
		xmlHttpReq = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		try {
			// to create XMLHttpRequest object in later versions
			// of Internet Explorer
			xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (exp1) {
			try {
				// to create XMLHttpRequest object in older versions
				// of Internet Explorer
				xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (exp2) {
				xmlHttpReq = false;
			}
		}
	}
	return xmlHttpReq;
}
/*
 * AJAX call starts with this function
 */
function makeRequest(url) {
//		alert("url===>"+url);
	var xmlHttpRequest = getXMLHttpRequest();
	xmlHttpRequest.onreadystatechange = getReadyStateHandler(xmlHttpRequest);
	xmlHttpRequest.open("GET", url, true);
	xmlHttpRequest.send();
}

/*
 * Returns a function that waits for the state change in XMLHttpRequest
 */
function getReadyStateHandler(xmlHttpRequest) {
	// an anonymous function returned
	// it listens to the XMLHttpRequest instance
	return function() {
		if (xmlHttpRequest.readyState == 4) {
			if (xmlHttpRequest.status == 200) {
				var all = xmlHttpRequest.responseText;
				alert(all);
				if (all.indexOf("login") > -1) {
					 
					if (all.indexOf("login") > -1) {
						window.location = "User.jsp?userdetails=" + all;
					}
				} else if (all.indexOf("Email/Password is Incorrect") > -1) {
					document.getElementById("adminname").value = "";
					document.getElementById("adminpass").value = "";
					document.getElementById("loginstatus").innerHTML = all;
				} 
				else if (all.indexOf("User doesn't exists") > -1) {
					document.getElementById("adminname").value = "";
					document.getElementById("adminpass").value = "";
					document.getElementById("loginstatus").innerHTML = all;
				}
				else if (all.indexOf("QRCodePath") > -1) {
					var path = all.split(",")[1];
					window.location = "User.jsp?qrcodepath=" + path;
				} else
					window.location = "Admin.jsp?list=" + all;
			}
		}
	}
};
