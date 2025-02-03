var saveInput = {
	saved: "",
	element: false,
	
	save: function(element) {
		saveInput.saved = element.value;
		saveInput.element = element;
	},
	
	check: function(element) {
		if(element == saveInput.element && element.value == "") element.value = saveInput.saved;
	}
}
/*
var liveClipboard = {
	xml: "",
	
	active: function(element) {
		var el = $(element)
		
		Element.addClassName(el, "active-clipboard")
		if(Element.hasClassName(el,"clause-clipboard")) {
			el.value = Document.serializeClause(Document.getClause(el))
		} else {
			el.value = Document.serializeSection(Document.getSection(el))
		}
		el.setSelectionRange(0, el.value.length)
		liveClipboard.xml = el.value
	},
	
	check: function(element) {
		var el = $(element)
		try {
			if(el && Element.hasClassName(el,"clipboard")) {
				if(el.value!="nil") {
					if(el.selectionEnd == el.selectionStart && el.value != liveClipboard.xml) {
						if(el.value == "") {
							if(Element.hasClassName(el,"clause-clipboard")) {
								Document.removeClause(el)
							} else {
								Document.removeSection(el)
							}
						} else {
							if((el.value).match(/<section>/)) {
								Document.pasteSection(el)
							} else {
								Document.pasteClause(el)
							}
						}
					}
					el.value="nil"
				}
				return null
			}
		}catch (e) {}
		var el = document.getElementsByClassName("active-clipboard", document)[0]
		if(el && el.selectionEnd == el.selectionStart) {
			liveClipboard.check(el)
		}
		Document.setIndex()
	},
	
	blur: function(element) {
		var el = $(element)
		liveClipboard.check(el)
		Element.removeClassName(el, "active-clipboard")
		el.value = "nil"
		liveClipboard.xml = ""
	},
	
	stopKeyPress: function(t, event) {
		if (event.keyCode) code = event.keyCode
		else if (event.which) code = event.which
		var character = String.fromCharCode(code)
		if(event.ctrlKey) return null
		Event.stop(event)
	},
	
	xhtml: function() {
		var container = document.createElement("span")		
				
		var input = document.createElement("input")
		input.setAttribute("type", "text")
		input.setAttribute("value", "nil")
		input.setAttribute("class", "clipboard")
		input.setAttribute("onfocus", "liveClipboard.active(this)")
		input.setAttribute("onblur", "liveClipboard.blur(this)")		
		
		container.appendChild(input)
		return container
	}
}
*/
var Section = Class.create();
Section.prototype = {
	initialize: function(tit, body) {
		this.sTitle = tit
		this.sBody = body
		this.clauses = []
	},
	
	addClause: function(cl) {
		this.clauses[this.clauses.length] = cl.xhtml()
	},
	
	xhtml: function() {
		var container = document.createElement("div")
		var section = document.createElement("div")
		var sectionhead = document.createElement("div")
		var sectionprologue = document.createElement("div")
		var h2 = document.createElement("h2")
		//var title = document.createElement("textarea")
		var body = document.createElement("div")
		var textarea = document.createElement("textarea")
		//var clipboard = liveClipboard.xhtml()
		
		//title.setAttribute("type", "text")
		
		Element.addClassName(section, "docElement")
		Element.addClassName(section, "section")
		Element.addClassName(sectionhead, "section-head")
		//Element.addClassName(title, "stitle")
		//Element.addClassName(title, "resize")
		Element.addClassName(body, "sbody")
		Element.addClassName(textarea, "paragraph")
		Element.addClassName(textarea, "resize")
		Element.addClassName(sectionprologue, "sprologue")
		//Element.addClassName(clipboard.firstChild, "section-clipboard")

       
		
		sectionhead.innerHTML += "<ul class=\"sectionactions\">" +
			"<li><img alt=\"\" src=\"/images/emblems/add.png\" /> <a href=\"#\" onclick=\"Document.addSection(this);" +
			" return false;\">Insert section</a> </li>" +
			"<li><img alt=\"\" src=\"/images/emblems/delete.png\" /> <a href=\"#\" onclick=\"Document.removeSection(this);" +
			" return false;\">Remove</a> </li>" +
			"</ul>";	   	   	
	
		for(i=0;i<this.clauses.length;i++) {
			body.innerHTML+=this.clauses[i]
		}
		
		//h2.appendChild(clipboard)
		//h2.appendChild(title)
		
		h2.innerHTML += "<textarea name=\""+ Math.floor(Math.random()*5)  + "\" class=\"stitle resize\" "
		  + " onfocus=\"saveInput.save(this);return false;\" onblur=\"saveInput.check(this);return false;\" >"+ this.sTitle +"</textarea>";
		
		sectionhead.appendChild(h2)
		
		showHideElements(sectionhead, 'UL', 'sectionactions')
		
		section.appendChild(sectionhead)
		section.appendChild(sectionprologue)
		sectionprologue.appendChild(textarea)
		section.appendChild(body)
		container.appendChild(section)
        
		//title.setAttribute("value",this.sTitle)
		//title.innerHTML = this.sTitle
		//title.setAttribute("onfocus","saveInput.save(this)")
		//title.setAttribute("onblur","saveInput.check(this)")
		//title.name = Math.floor(Math.random()*5)
		textarea.innerHTML = this.sBody
		textarea.name = Math.floor(Math.random()*5)
		do {
			var id = Math.floor(Math.random()*1000)
			section.setAttribute("id","sc"+id)
		} while($("sc"+id))

		section.innerHTML += "<p><img alt=\"Add\" src=\"/images/emblems/add.png\" /> <a href=\"#\" onclick=\"Document.addClause(this); return false;\">Add clause</a></p>"		
		
		//container.innerHTML += "<script>new Effect.Highlight('sc"+id+"'); Document.dinTextareas();</script>"
		container.innerHTML += "<script>Document.dinTextareas();</script>"
		Element.cleanWhitespace(container)
		
		return container.innerHTML
		
	}
}

var Clause = Class.create();
Clause.prototype = {
	initialize: function(tit, bod) {
		this.cTitle = tit
		this.cBody  = bod
	},

	xhtml: function(base) {
		var container = document.createElement("div")
		var clause = document.createElement("div")
		var clausehead = document.createElement("div")
		var h3 = document.createElement("h3")
		//var title = document.createElement("textarea")
		var body = document.createElement("div")
		var textarea = document.createElement("textarea")
		//var clipboard = liveClipboard.xhtml()
		
		//title.setAttribute("type", "text")
		textarea.setAttribute("cols","65")
		
		if(base) {
			Element.addClassName(clause, "docElement")
		}
		Element.addClassName(clause, "clause")
		Element.addClassName(clausehead, "clause-head")
		//Element.addClassName(title, "ctitle")
		//Element.addClassName(title, "resize")
		Element.addClassName(body, "cbody")
		Element.addClassName(textarea, "paragraph")
		Element.addClassName(textarea, "resize")
		//Element.addClassName(clipboard.firstChild, "clause-clipboard")
		
		if(base) {
			//"<li><img alt=\"\" src=\"/images/emblems/comments.png\" /> <a href=\"#comments-link\" >Comments</a> </li>"+
			clausehead.innerHTML = "<ul class=\"clauseactions\">"+
				"<li><img alt=\"\" src=\"/images/emblems/add.png\" /> <a href=\"#\" onclick=\"Document.addSection(this); return false;\">Insert section</a> </li>"+
		    "<li><img alt=\"\" src=\"/images/emblems/add.png\" /> <a href=\"#\" onclick=\"Document.addClause(this); return false;\">Insert clause</a> </li>"+
		    "<li><img alt=\"\" src=\"/images/emblems/delete.png\" /> <a href=\"#\" onclick=\"Document.removeClause(this); return false;\">Remove</a></li>"+
				"</ul>";
		} else {
			//"<li><img alt=\"\" src=\"/images/emblems/comments.png\" /> <a href=\"#comments-link\" >Comments</a> </li>"+
			clausehead.innerHTML = "<ul class=\"clauseactions\">"+
		    "<li><img alt=\"\" src=\"/images/emblems/add.png\" /> <a href=\"#\" onclick=\"Document.addClause(this); return false;\">Insert clause</a> </li>"+
		    "<li><img alt=\"\" src=\"/images/emblems/delete.png\" /> <a href=\"#\" onclick=\"Document.removeClause(this); return false;\">Remove</a></li>"+
				"</ul>";
		}
		
		//h3.appendChild(clipboard)
		//h3.appendChild(title)
		h3.innerHTML += "<textarea name=\""+ Math.floor(Math.random()*5) + "\" class=\"ctitle resize\" "
		  + " onfocus=\"saveInput.save(this);return false;\" onblur=\"saveInput.check(this);return false;\" >"+ this.cTitle +"</textarea>";
		
		  		
		
		clausehead.appendChild(h3)
		
		showHideElements(clausehead, 'UL', 'clauseactions')
		
		clause.appendChild(clausehead)
		body.appendChild(textarea)
		clause.appendChild(body)
		container.appendChild(clause)

		//title.innerHTML = this.cTitle
		//title.setAttribute("onfocus","saveInput.save(this)")
		//title.setAttribute("onblur","saveInput.check(this)")
		//title.name = Math.floor(Math.random()*5)
		textarea.innerHTML = this.cBody
		textarea.name = Math.floor(Math.random()*5)
		do {
			var id = Math.floor(Math.random()*1000)
			clause.setAttribute("id","sc"+id)
		} while($("sc"+id))
		
		//container.innerHTML += "<script>new Effect.Highlight('sc"+id+"')</script>"
		container.innerHTML += "<script>Document.dinTextareas();</script>"
		
		Element.cleanWhitespace(container)
			
		return container.innerHTML
	}
}

var DocumentClass = Class.create();
DocumentClass.prototype = {
	initialize: function() {
		this.contract = $("contract")
		this.clauses = $("docBody")
		this.docIndex = $("docIndex")
	},
	
	getClause: function(element) {
		return $(element).parentNode.parentNode.parentNode.parentNode
	},
	
	getSection: function(element) {
		return $(element).parentNode.parentNode.parentNode.parentNode
	},
	
	addSection: function(element, section) {
	   
		var section = (section != null?section:new Section("New section", "Insert text here."))
		var el = $(element)
		if (el && el != this.clauses) {
			new Insertion.Before(this.getSection(el), section.xhtml())
		} else {
			new Insertion.Bottom(this.clauses, section.xhtml())
		}
		//this.docIndex.innerHTML = this.getIndex()
		
	},
	
	pasteSection: function(element) {
		var sect = element.value
		var title = sect.match(/<title>.*?<\/title>/)[0].replace(/<[^>]+>/g,"")
		var section = new Section(title, "")
		var cl = sect.match(/<clause>.*?<\/clause>/g)
		for(j=0;j<cl.length;j++) {
			var title = cl[j].match(/<title>.*<\/title>/)[0].replace(/<[^>]+>/g,"")
			var p = cl[j].match(/<p>.*?<\/p>/g)
			var body = ""
			for(i=0;i<p.length;i++) {
				body += p[i].replace(/<\/?p>/g,"") + "\n"
			}
			section.addClause(new Clause(title, body))
		}
		this.addSection(element, section)
		this.dinTextareas("load")
	},
	
	addClause: function(element, clause) {
	   
		var clause = clause?clause:new Clause("new clause", "Insert text here.")
		var el = $(element)
		if (el && el != this.clauses) {
			
			if (Element.hasClassName(el.parentNode.parentNode, "section")) {
				// Add clause inside a section
				el = el.parentNode.parentNode
				el = document.getElementsByClassName("sbody",el)[0]
				new Insertion.Bottom(el, clause.xhtml())
			} else {
				if (el.tagName=="INPUT" || Element.hasClassName(el,"docElement")) {
					new Insertion.Before(this.getClause(el), clause.xhtml(true))
				} else {
					new Insertion.Before(this.getClause(el), clause.xhtml())
				}
			}
		} else {
			new Insertion.Bottom(this.clauses, clause.xhtml(true))
		}
		//this.docIndex.innerHTML = this.getIndex()
		
	},

	pasteClause: function(element) {
		var cl = element.value
		var title = cl.match(/<title>.*<\/title>/)[0].replace(/<[^>]+>/g,"")
		var p = cl.match(/<p>.*?<\/p>/g)
		var body = ""
		for(i=0;i<p.length;i++) {
			body += p[i].replace(/<\/?p>/g,"") + "\n"
		}
		this.addClause(element, new Clause(title, body))
		this.dinTextareas("load")
	},
	
	removeSection: function(element) {
		Element.remove(this.getSection(element))
		//this.docIndex.innerHTML = this.getIndex()
	},
	
	removeClause: function(element) {
		Element.remove(this.getClause(element))
		//this.docIndex.innerHTML = this.getIndex()
	},
	
	escape: function(text) {
		if(text)
			return text.replace(/</g, '&lt;').replace(/&/g, '&amp;')
		else
			return ""
	},
	
	serializeClause: function(cl) {
		var title, paragraph, content
		var output = "<clause>"
		title = document.getElementsByClassName("ctitle", cl)[0]
		saveInput.check(title)
		output += "<title>"+this.escape(title.value)+"</title>"
		paragraph = document.getElementsByClassName("paragraph", cl)
		output += "<body>"
		for(j=0;j<paragraph.length;j++) {
			content = this.escape(paragraph[j].value)
			if(content.length > 0 || j==0) {output += "<p>"+content+"</p>"}
		}
		output += "</body>"
		output += "</clause>"
		return output
	},
	
	serializeSection: function(sect) {
		var title, paragraph, content
		var output = "<section>"
		title = document.getElementsByClassName("stitle", sect)[0]
		saveInput.check(title)
		if(title) output += "<title>"+this.escape(title.value)+"</title>"
		paragraph = document.getElementsByClassName("paragraph", sect)
		output += "<body>"
		for(j=0;j<paragraph.length;j++) {
			if(!Element.hasClassName(paragraph[j].parentNode.parentNode,"section")) { break }
			content = this.escape(paragraph[j].value)
			if(content.length > 0 || j==0) {output += "<p>"+content+"</p>"}
		}
		output += "</body>"
		output += "<clauses>"
		var clauses = document.getElementsByClassName("clause",sect)
		for(k=0;k<clauses.length;k++) {
			output += this.serializeClause(clauses[k])
		}
		output += "</clauses>"
		output += "</section>"
		return output
	},
	
	serialize: function() {
		var doc = this.clauses
		var output = "<document>"
		var el = document.getElementsByClassName("docElement", doc)
		for (i=0;i<el.length;i++) {
			if(Element.hasClassName(el[i],"clause")) {
				output += this.serializeClause(el[i])
			} else {
				output += this.serializeSection(el[i])
			}
		}
		output += "</document>"
		return output
	},
	
	/*setIndex: function() {
		this.docIndex.innerHTML = this.getIndex()
	},
	
	getIndex: function() {
		var doc = this.clauses
		var output = "<ol>"
		var el = document.getElementsByClassName("docElement", doc)
		var title, clauses, cls, id
		for (i=0;i<el.length;i++) {
			id = el[i].getAttribute("id")
			if(Element.hasClassName(el[i],"clause")) {
				title = document.getElementsByClassName("ctitle", el[i])[0]
				saveInput.check(title)
				output += "<li><a href=\"#"+id+"\">"+this.escape(title.value)+"</a></li>"
			} else {
				title = document.getElementsByClassName("stitle", el[i])[0]
				saveInput.check(title)
				output += "<li><a href=\"#"+id+"\">"
				if(title) output += this.escape(title.value)
				output += "</a>"
				clauses = document.getElementsByClassName("clause",el[i])
				cls = clauses.length
				if(cls>0) output += "<ol>"
				for(k=0;k<cls;k++) {
					id = clauses[k].getAttribute("id")
					title = document.getElementsByClassName("ctitle", clauses[k])[0]
					saveInput.check(title)
					output += "<li><a href=\"#"+id+"\">"+this.escape(title.value)+"</a></li>"
				}
				if(cls>0) output += "</ol>"
				output += "</li>"
			}
		}
		output += "</ol>"
		return output
	},*/
	
	submit: function() {
		var element = $("docvalue")
		element.value = this.serialize()
		$("doctitle").value = $("documentTitle").value
	},
	
	dinTextareas: function(event) {
		var areas = document.getElementsByClassName("resize",this.clauses);
		if(event == "load") {
			resize($("documentTitle"));
		}
		for(i=0;i<areas.length;i++) {
			//areas[i].onfocus = din_textarea;
			areas[i].onkeyup = din_textarea;
			areas[i].onkeypress = din_textarea;
			if(Element.hasClassName(areas[i], "paragraph")) {
			 areas[i].onblur = din_textarea;
			}
			//XXX: error en explorer areas[i].onkeyup(event);
			if(event == "load") {
				areas[i].focus();
			}
			resize(areas[i]);
		}
	}
}

function din_textarea(event) {
    if (window.event) {
			event = window.event;
		}
	try {
		/*if((event.type == "keyup" || event.type == "keypress" ) && this.value.match(/[\n\r]/)) {
			Event.stop(event);
		} else*/ 
		if(event && event.type != "keyup" && (event.type != "keypress" || event.keyCode==8)) {		    
			if (this.value.length==0) {				
				//if(this.hasFocus) {
					if(this.previousSibling &&
											this.previousSibling.tagName == "TEXTAREA") {						
						var len = this.previousSibling.value.length;
						//this.previousSibling.setSelectionRange(len, len);
						setSelectionRange(this.previousSibling, len, len);						
						this.parentNode.removeChild(this);
						this.previousSibling.focus();		
					}else if(this.nextSibling &&
											this.nextSibling.tagName == "TEXTAREA") {						
						this.nextSibling.focus();
						//this.nextSibling.setSelectionRange(0, 0);
						setSelectionRange(this.nextSibling, 0, 0);
						this.parentNode.removeChild(this);
					}
					//range.moveStart();
				//}
			}
		}
	
		if(event && event.type=="keypress") {
		    
			if ((event.keyCode == 46 || event.keyCode == 63272) &&
							this.selectionEnd == this.selectionStart &&
							this.selectionEnd == this.value.length) {
				if(this.nextSibling &&
										this.nextSibling.tagName == "TEXTAREA") {
				    
					var sel = this.selectionEnd;
					this.value+=this.nextSibling.value;
					//this.setSelectionRange(sel, sel);
					setSelectionRange(this, sel, sel);
					this.parentNode.removeChild(this.nextSibling);
					Event.stop(event);
				}
			} else if (event.keyCode == 8 &&			                 
							this.selectionEnd == this.selectionStart &&
							this.selectionStart == 0) {
				if(this.previousSibling &&
										this.previousSibling.tagName == "TEXTAREA") {					
					var sel2 = this.previousSibling.value.length;
					this.previousSibling.value+=this.value;
					this.previousSibling.focus();
					//this.previousSibling.setSelectionRange(sel2, sel2);
					setSelectionRange(this.previousSibling, sel2, sel2);
					this.parentNode.removeChild(this);
					
					Event.stop(event);
				}
			} else if((event.keyCode == 37 || event.keyCode == 38) &&
								this.previousSibling &&
								this.previousSibling.tagName == "TEXTAREA" &&
								this.selectionEnd == this.selectionStart &&
								this.selectionStart == 0) {
					
				var sel2 = this.previousSibling.value.length;
				this.previousSibling.focus();
				//this.previousSibling.setSelectionRange(sel2, sel2);
				setSelectionRange(this.previousSibling, sel2, sel2);
				Event.stop(event);
			} else if((event.keyCode == 39 || event.keyCode == 40) &&
									this.nextSibling &&
									this.nextSibling.tagName == "TEXTAREA" &&
									this.selectionEnd == this.selectionStart &&
									this.selectionStart == this.value.length) {
					this.nextSibling.focus();
					//this.nextSibling.setSelectionRange(0,0);
					setSelectionRange(this.nextSibling, 0,0);
					Event.stop(event);
				}
		}
	} catch(e) {}
	//if(event && event.type=="keypress") alert(event.keyCode+" "+event.keyCode);
	resize(this);
	//if(event) alert(this.style.fontSize);
}

function resize(element) {
	element.value = element.value.replace(/\r/g,'');
	if(Element.hasClassName(element, "paragraph") && element.value.match(/\n\r?/)) {
		element.value = element.value.replace(/^\n+/,'');
		var bloques = element.value.split("\r\n");
		if (bloques.length < 2) {
			bloques = element.value.split("\n");
		}
		var pointer = element;
		element.value = bloques[0].replace(/\n+/g,"");
		element.value = bloques[0].replace(/\r\n/g,"");
		
		for(i=1;i<bloques.length;i++) {
			var textarea = document.createElement("textarea");
			//textarea.setAttribute("cols","65");
			Element.addClassName(textarea, "resize");
			Element.addClassName(textarea, "paragraph");
			textarea.value = bloques[i].replace(/\n+/g,'');
			textarea.value = bloques[i].replace(/\r\n/g, "");
			element.parentNode.insertBefore(textarea, pointer.nextSibling);
			pointer = textarea;
			textarea.name = Math.floor(Math.random()*5);
			textarea.onkeyup = din_textarea;
			textarea.onkeypress = din_textarea;
			textarea.onblur = din_textarea;
			//textarea.onfocus = din_textarea;
			resize(textarea);
			//==================
			//element LOAD FOCUS AND HOVER EVENTS FOR IE THAT DOESN'T SUPPORT HOVER AND FOCUS CSS
			/*textarea.onfocus = function() {
			   Element.addClassName(element, 'paragraphFocus');
			}
			textarea.onblur = function() {
			   Element.removeClassName(element, 'paragraphFocus');
			}*/
			textarea.onmouseover = function() {
			   Element.addClassName(element, 'editorHover');
			}
			textarea.onmouseout = function() {
			   Element.removeClassName(element, 'editorHover');
			}
			
			//===================
			
			//if(textarea.value.length>0) textarea.onkeyup(event);
			var length = textarea.value.length;					
			//textarea.setSelectionRange(length, length);
			setSelectionRange(textarea, length, length);
        /*        
			if(element.value.length>0) {					   
				//if(event) textarea.focus();
				if(event && event.type != "load") {
					
				  	textarea.value = '';
					textarea.focus();
				}
			}
			*/
		}
	} else {				
		element.value = element.value.replace(/\n+/g,"");
		//CARRIAGE RETURN FOR IE AND OPERA IS \r\n
		element.value = element.value.replace(/\r\n/g,"");
	}
	//------------
	if(Element.hasClassName(element, "paragraph")) var tmp = document.getElementById("ptmp");
	if(Element.hasClassName(element, "stitle")) var tmp = document.getElementById("stmp");
	if(Element.hasClassName(element, "ctitle")) var tmp = document.getElementById("ctmp");
	if(Element.hasClassName(element, "title")) var tmp = document.getElementById("ttmp");
	
	if(!(tmp)) {
		tmp = document.createElement("div");		
		var appendParagraph = false;
		
		if(Element.hasClassName(element, "paragraph")) {
			tmp.setAttribute("id","tmp");
			Element.addClassName(tmp, "paragraph");
			appendParagraph = true;
		} else if(Element.hasClassName(element, "stitle")) {
			tmp.setAttribute("id","stmp");
			Element.addClassName(tmp, "stitle");
		} else if(Element.hasClassName(element, "ctitle")) {
			tmp.setAttribute("id","ctmp");
			Element.addClassName(tmp, "ctitle");
		}	else if(Element.hasClassName(element, "title")) {
			tmp.setAttribute("id","ttmp");
			Element.addClassName(tmp, "title");
		}
		tmp.style.position ="absolute";
		tmp.style.zInxex = "10";
		tmp.style.top="50px";
		//tmp.style.width = ((element.style.width || element.offsetWidth)-10)+'px';
		tmp.style.width = element.style.width ? (element.style.width - 10)+'px' : (element.offsetWidth - 10)+'px';
		tmp.style.visibility="hidden";
		/*
		tmp.style.padding = "5px";
		tmp.style.fontSize = ".9em";
		tmp.style.fontFamily = element.style.fontFamily;
		*/
		try {
			document.firstChild.appendChild(tmp);			
		} catch(e) {
			document.getElementsByTagName("body")[0].appendChild(tmp);			
		}
		
	}
	
	var empty = false;
	if(element.value == "") {
		empty=true;
		element.value = "a";
	}
	tmp.innerHTML = element.value;
	var size = tmp.offsetHeight;
	//tmp.innerHTML = "";
	
	/*
	var cols = element.getAttribute("cols");
	var regexp = new RegExp("[^\r\n]{"+cols+","+cols+"}","g");
	var lineas = element.value.replace(regexp,"\n").replace(/[^\n]/g,'').length;
	*/
	//element.style.height=(lineas+2)+".5em";
	element.style.height = (size)+"px";
	if(empty) {
		element.value="";
	}
}

function setSelectionRange(input, selectionStart, selectionEnd) {
    if (input.setSelectionRange) {
        input.focus();
        input.setSelectionRange(selectionStart, selectionStart);
    } else if (input.createTextRange) {
        var range = input.createTextRange();
        range.collapse(true);
        range.moveEnd('character', selectionEnd);
        range.moveStart('character', selectionStart);
        range.select();
    }
}

function loadHover() {

    var sections = document.getElementsByClassName('section-head');
    showHideElements( sections, 'UL', 'sectionactions');

    var clauses = document.getElementsByClassName('clause-head');
    showHideElements( clauses, 'UL', 'clauseactions');

    var textareas = document.getElementsByTagName('TEXTAREA');
    
    for (i = 0; i < textareas.length; i++) {
        var textarea = textareas[i];
        if (Element.hasClassName(textarea, 'title')    
                || Element.hasClassName(textarea, 'stitle') 
                || Element.hasClassName(textarea, 'ctitle')
                || Element.hasClassName(textarea, 'paragraph')) {
            textarea.onmouseover = function() {
                Element.addClassName(this, 'editorHover');
            }
            textarea.onmouseout = function() {
                Element.removeClassName(this, 'editorHover');
            }
						/*
            textarea.onfocus = function() {
                Element.addClassName(this, 'editorFocus');
            }         
            textarea.onblur = function() {
                Element.removeClassName(this, 'editorFocus');
            }
*/
        }
    }    
}

function showHideElements(elements, tagName, className) {
    
	
    for (i = 0; i < elements.length; i++) {
        element = elements[i];
        
        element.onmouseover = function() {            
            for (k = 0; k < this.childNodes.length; k++) {
                if (this.childNodes[k].tagName &&
                    this.childNodes[k].tagName == tagName && 
                        Element.hasClassName(this.childNodes[k], className)) {
                   this.childNodes[k].style.visibility = 'visible';
                   //Element.addClassName(this.childNodes[k], 'visible');
                }
            }
            closeOtherMenus(this);         
        }
        element.onmouseout = function() {            
            node = this;
            tag = tagName
            classN = className;
            setTimeout("hiddenMenu()", 3000);            
        }
        
    }
    
}

var node = null;
var tag;
var classN;
function hiddenMenu() {
    if (node != null) {
        for (k = 0; k < node.childNodes.length; k++) {
            if (node.childNodes[k].tagName &&
                node.childNodes[k].tagName == tag && 
                    Element.hasClassName(node.childNodes[k], classN)) {
                node.childNodes[k].style.visibility = 'hidden';
                //Element.removeClassName(this.childNodes[k], 'visible');
            }
        }
    }
    node = null;
}

function closeOtherMenus(element) {
     var sections = document.getElementsByClassName('section-head');
     for (i = 0; i < sections.length; i++) {
        if (sections[i] != element) {
            node = sections[i];
            tag = 'UL';
            classN = 'sectionactions';
            hiddenMenu();
        }
     }
     
     //showHideElements( sections, 'UL', 'sectionactions');
     var clauses = document.getElementsByClassName('clause-head');
     for (i = 0; i < clauses.length; i++) {
        if (clauses[i] != element) {
            node = clauses[i];
            tag = 'UL';
            classN = 'clauseactions';
            hiddenMenu();
        }
     }
    //showHideElements( clauses, 'UL', 'clauseactions');
}