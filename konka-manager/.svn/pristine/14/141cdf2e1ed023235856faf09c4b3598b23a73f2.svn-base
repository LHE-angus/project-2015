function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter", function() {
		return false;
	})
	$(this).keypress(function() {
		if (!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))
			this.value = this.t_value;
		else
			this.t_value = this.value;
		if (this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))
			this.o_value = this.value
	}).keyup(function() {
		if (!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))
			this.value = this.t_value;
		else
			this.t_value = this.value;
		if (this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))
			this.o_value = this.value
	}).blur(function() {
		if (!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))
			this.value = this.o_value;
		else {
			if (this.value.match(/^\.\d+$/))
				this.value = 0 + this.value;
			if (this.value.match(/^\.$/))
				this.value = 0;
			this.o_value = this.value
		}
		if (this.value.length == 0)
			this.value = "0";
	})
	//this.text.selected;
}