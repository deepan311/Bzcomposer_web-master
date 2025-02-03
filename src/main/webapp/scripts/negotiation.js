//
// Comments
//
var comments = {
  show: function() {    
    showElement('comments-container-id')
	Element.hide('commentsSpinner')
  },

  hide: function() {
    hideElement('comments-container-id')
  },

	request: function(id) {
		new Ajax.Updater('comments-container-id', '/negotiation/comments/'+id, 
		  {asynchronous:true, evalScripts:true, 
		  onSuccess: function(request){comments.show()}, 
		  onLoading:function(request){Element.show('commentsSpinner')}}); return false;
	},

  toggle: function(id) {
    toggleLink('comments-link')    
    Element.visible('comments-container-id') ? this.hide() : this.request(id)
  }
}

//
// Negotiation Parts
//
var negotiationParts = {
  show: function(id) {
    showElement('part-module-contents['+id+']')
  },
  
  hide: function(id) {
    hideElement('part-module-contents['+id+']')
  },
  
  toggle: function(id) {
    toggleLink('togglePartLink['+id+']')
    Element.visible('part-module-contents['+id+']') ? this.hide(id) : this.show(id)
  }
}

//
// Negotiation Invitation
//
var negotiationInvitation = {
  show: function(id) 
  {
  
    showElement('invitePeople['+id+']', { afterFinish: function()
     {  
           
			$('emailInvitePeople['+id+']').focus()
		}})
		Element.addClassName('menu',"links-lock")
  },
  
  hide: function(id)
   {
    hideElement('invitePeople['+id+']', { afterFinish: function() {
			Element.removeClassName('menu',"links-lock")	
		}});
  },

	submit: function(id) {
		showElement('invitePeopleSpinner['+id+']', { afterFinish: function() 
		{  
			hideElement('invitePeople['+id+']');
			Element.removeClassName('menu',"links-lock");
		}});
	},
  
  toggle: function(id) {
    toggleLink('invitePeopleLink['+id+']')
    Element.visible('invitePeople['+id+']') ? this.hide(id) : this.show(id)
  }
}

//
// Part Invitation
//
var partInvitation = {
  show: function() {
    showElement('invitePart', { afterFinish: function() {
			$("emailInvitePart").focus();
		}})
 	  hideElement('invitePartText')
		Element.addClassName('menu',"links-lock")
  },
  
  hide: function() {
    hideElement('invitePart', { afterFinish: function() {
	    showElement('invitePartText');
			Element.removeClassName('menu',"links-lock");
		}});
  },
	
  submit: function() {
		showElement('invitePartSpinner', { afterFinish: function() {
    	hideElement('invitePart');
 			Element.removeClassName('menu',"links-lock");
		}});
	},
  
  toggle: function() {
    Element.visible('invitePart') ? this.hide() : this.show()
  }
}

//
// Edit Participant
//
var editParticipant = {
  show: function(id) 
  {     
		Element.addClassName('menu',"links-lock")
		hideElement('participant-module['+id+']', { afterFinish: function() {
		  showElement('editParticipant['+id+']');
  	}});
  },
  
  hide: function(id) {
    hideElement('editParticipant['+id+']', {afterFinish: function() {
			showElement('participant-module['+id+']');
			Element.removeClassName('menu',"links-lock");
		}});
  },

	submit: function(id) {
		showElement('participantSpinner['+id+']', { afterFinish: function() {
			hideElement('editParticipant['+id+']');
			Element.removeClassName('menu',"links-lock");
		}});
	},
	
	visible: function(id) {
		visible = $("participantForm["+id+"]_visible")
		signer = $("participantForm["+id+"]_signer")

		if (visible.checked) {
			Element.addClassName('editParticipant['+id+']',"visible")
			Element.removeClassName('editParticipant['+id+']',"invisible")
		} else {
			visible.checked = false
			signer.checked = false
			Element.removeClassName('editParticipant['+id+']',"visible")
			Element.addClassName('editParticipant['+id+']',"invisible")
		}
	},
  
  toggle: function(id) {
    toggleLink('editParticipantLink['+id+']')
    Element.visible('editParticipant['+id+']') ? this.hide(id) : this.show(id)
  }
}

//
// Edit Participant
//
var removeParticipant = {
  show: function(id) {
		Element.addClassName('menu',"links-lock")
		hideElement('participant-module['+id+']', { afterFinish: function() {
		  showElement('removeParticipant['+id+']');
  	}});
  },
  
  hide: function(id) {
    hideElement('removeParticipant['+id+']', {afterFinish: function() {
			showElement('participant-module['+id+']');
			Element.removeClassName('menu',"links-lock");
		}});
  },

	submit: function(id) {
		showElement('participantSpinner['+id+']', { afterFinish: function() {
			hideElement('removeParticipant['+id+']');
			Element.removeClassName('menu',"links-lock");
		}});
	},
  
  toggle: function(id) {
    toggleLink('removeParticipantLink['+id+']')
    Element.visible('removeParticipant['+id+']') ? this.hide(id) : this.show(id)
  }
}

//
// Compare versions
//
var versions = {
  show: function() {
    showElement('older-versions-module')
  },
  
  hide: function(id) {
    hideElement('older-versions-module');
  },
  
  toggle: function(id) {
    toggleLink('olderVersionsLink')
    Element.visible('older-versions-module') ? this.hide() : this.show()
  },
  
  getSelected: function() {
    var selected = new Array();
    var elements = $$('.versions-module input');

    for(var i = 0; i < elements.length; i++) {
      if(elements[i].name == "version[]" && elements[i].checked) {
        selected.push(elements[i])
      }
    }
    
    return selected;
  },
  
  checked: function(checkbox) 
  {
    var selected = this.getSelected();
    if (selected.length > 2) {
      for(var i = 0; i < selected.length; i++) {
        if(checkbox != selected[i]) {
          selected[i].checked = false
        }
      }
    }
    this.testCompareButton();
  },
  
  testCompareButton: function() {
    $('compare_button').disabled = (this.getSelected().length != 2)
  }
}
