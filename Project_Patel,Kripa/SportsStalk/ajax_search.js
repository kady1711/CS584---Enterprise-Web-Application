var req;

var IE_instance;
var s_query;
var s_table;

function init()
{
	s_table = document.getElementById("sTable");
	s_query = document.getElementById("search-input");
}

function AutoComplete()
{
	var pageUrl = "AutoCompleteServlet?query=find&search_query=" + escape(s_query.value);
	req = getReqInstance();
	req.open("GET",pageUrl,true);
	req.onreadystatechange = getReqState;
	req.send(null);
}

function getReqInstance()
{
	if(window.XMLHttpRequest)
	{
		 if (navigator.userAgent.indexOf('MSIE') != -1) {
            IE_instance = true;
        }
        return new XMLHttpRequest();
	}
	else if (window.ActiveXObject) 
	{
        IE_instance = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
	else
	{
		return null; 
	}
}

function getReqState()
{
	clearTable();

    if (req.readyState == 4) {
        if (req.status == 200) {
            displayTable(req.responseXML);
        }
    }
}

function clearTable()
{
	var j;
	if(s_table.getElementsByTagName("tr").length > 0)
	{
		s_table.style.display = 'none';
		for(j = s_table.childNodes.length-1;j >= 0;j--)
		{
			s_table.removeChild(s_table.childNodes[j]);
		}
	}
}

function displayTable(responseXML) {
    
    if (responseXML != null) 
	{
        var events_collection = responseXML.getElementsByTagName("events")[0];
        if (events_collection.childNodes.length > 0) 
		{
			s_table.setAttribute("border", "1");
            s_table.setAttribute("bordercolor", "black");
			var i;
            for (i = 0; i < events_collection.childNodes.length; i++) 
			{
                var p_instance = events_collection.childNodes[i];
				var p_name = p_instance.getElementsByTagName("eventName")[0];
                var p_id = p_instance.getElementsByTagName("eventId")[0];    
				var name_val=p_name.childNodes[0].nodeValue;
				
				var anchor_value;
				var r;
				var c;
			
				if (IE_instance) 
				{
					s_table.style.display = 'block';
					r = s_table.insertRow(s_table.rows.length);
					c = r.insertCell(0);
				}  
				else 
				{
					s_table.style.align = 'right';
					s_table.style.display = 'table';
					s_table.style.width = '300px';
					s_table.style.background = '#fff';
					s_table.style.position = 'absolute';
					
					r = document.createElement("tr");
					c = document.createElement("td");
					r.appendChild(c);
					s_table.appendChild(r);
				}
				
				
				anchor_value = document.createElement("a");
				anchor_value.className = "Item";
				anchor_value.setAttribute("href", "AutoCompleteServlet?query=display&search_query=" + name_val);
				anchor_value.appendChild(document.createTextNode(name_val));
				c.className = "Cell";
				c.appendChild(anchor_value);
            }
        }

    } 
	else 
	{
		return false;
	}
}	