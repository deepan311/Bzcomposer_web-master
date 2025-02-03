// Place your application-specific JavaScript functions and classes here
// This file is automatically included by javascript_include_tag :defaults

function toggleLink(link) {
  link = $(link)

  var inactiveText = link['neg:inactiveText'] || link.getAttribute('neg:inactiveText')
  
  if(!inactiveText) {
    link['neg:inactiveText'] = link.innerHTML
    inactiveText = link.innerHTML
  }

  var activeText = link.getAttribute('neg:activeText')

  if(link['neg:active']) {
    link.innerHTML = inactiveText
    link['neg:active'] = false
  } else {
    link.innerHTML = activeText
    link['neg:active'] = true
  }
}

function showElement(element, options) {
	try{
  if($(element) && !Element.visible(element)) {
    options = options || {}
    new Effect.BlindDown(element, {duration: 0.3,
      afterFinish: function() {
        //Element.undoClipping(element)
        $(element).style.width = "auto"
        $(element).style.height = "auto"
        if(options.afterFinish) options.afterFinish()
      }})
  }
	}catch(e){}
}

function hideElement(element, options) {
	try{
  if($(element) && Element.visible(element)) {
    options = options || {}
    new Effect.BlindUp(element, {duration: 0.3,
      afterFinish: function() {
        //Element.undoClipping(element)
        Element.hide(element)
        if(options.afterFinish) options.afterFinish()
      }})
  }
	}catch(e){}
}

//
// Flasbox
//
var flashBox = {
	hide: function() {
		hideElement("flashbox-container-id")
	}
}

var FlashObserver = Class.create();
FlashObserver.prototype = {
	initialize: function() {
		this.flash = false
	},
	
	add: function() {
		if (this.flash) {
			clearTimeout(this.flash)
		}
		this.flash = setTimeout(flashBox.hide,15000)
	}
}
var FlashTimeouts = new FlashObserver();

function selectChaseBankFromDropDownList(elementID) {
    var depositBank = document.getElementById(elementID);
    for (var i = 0; i < depositBank.options.length; i++) {
        if (depositBank.options[i].text.toLowerCase() === "chase bank") {
            depositBank.selectedIndex = i;
            break;
        }
    }
}

function selectPaymentTypeAsCheckFromDropDownList(elementID) {
    var selectedElement = document.getElementById(elementID);
    for (var i = 0; i < selectedElement.options.length; i++) {
        if (selectedElement.options[i].text.toLowerCase() === "check") {
            selectedElement.selectedIndex = i;
            break;
        }
    }
}

function selectCategoryAsPurchaseOrder6800FromDropDownList(elementID) {
    var selectedElement = document.getElementById(elementID);
    for (var i = 0; i < selectedElement.options.length; i++) {
        if (selectedElement.options[i].text.toLowerCase() === "purchase order 6800") {
            selectedElement.selectedIndex = i;
            break;
        }
    }
}
