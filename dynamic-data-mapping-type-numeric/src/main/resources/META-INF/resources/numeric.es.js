import Component from 'metal-component';
import Soy from 'metal-soy';
import Inputmask from "inputmask";

import templates from './numeric.soy';

/**
 * Numeric Component
 */
class Numeric extends Component {}

// Register component
Soy.register(Numeric, templates, 'render');

if (!window.DDMNumeric) {
	window.DDMNumeric = {

	};
}

window.DDMNumeric.render = Numeric;
window.DDMNumeric.Inputmask = Inputmask;

export default Numeric;