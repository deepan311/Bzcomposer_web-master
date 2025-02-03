/*
Copyright 2005, 4word systems
All rights reserved.

This software may not be reproduced or distributed in any form without the express 
written consent of 4word systems or it's designee.

Revision 1.1:  20050729 Added underscore to list of valid characters
*/


function isValidEmail(email) {
	var pattern=/^[_0-9a-zA-z]+(\.[_A-Za-z0-9]+)*@[A-Za-z0-9]+(\.[A-Za-z]+)+$/;
    if (!pattern.test(email)) {
        return false;
    }
	else
	{
		return false;
	}
}
