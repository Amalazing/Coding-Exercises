// create an svg canvas by hooking into existing DOM element
const svg = d3
    .select('.canvas')
    .append('svg')
    .attr('width', 600)
    .attr('height', 600);

// Need margin so that we have room for both axises
const margin = { top: 20, right: 20, bottom: 100, left: 100 };

// This is the total width of the graph itself
const graphWidth = 600 - margin.left - margin.right;

// This is the total height of the graph itself
const graphHeight = 600 - margin.top - margin.bottom;

// Create the graph and put it in a group to easily transorm and translate all bars
const graph = svg
    .append('g')
    .attr('width', graphWidth)
    .attr('height', graphHeight)
    .attr('transform', `translate(${margin.left}, ${margin.top})`);

// Create the group that contains the x-axis
const xAxisGroup = graph
    .append('g')
    .attr('transform', `translate(0, ${graphHeight})`); // Need to transform becuase d3 defualt x-axis starts at top

// Create the group that contains the y-axis
const yAxisGroup = graph.append('g');

// This function grabs the data as a promise then returns a callback function to create the graph
d3.json('data.json').then(data => {

    // Create the Y linear scale so that everything fits onto the Y-axis without looking stupid
    const y = d3
        .scaleLinear()
        .domain([0, d3.max(data, d => d.orders)]) // Domain is the range of values found in data
        .range([graphHeight, 0]); // Range is tall you want the graph visually

    // Grabs the min of the values for the orders property in the data
    const min = d3.min(data, d => d.orders);

    // Grabs the max of the values for the orders property in the data
    const max = d3.max(data, d => d.orders);

    // Grabs the min and max of the values for the orders property in the data
    const extent = d3.extent(data, d => d.orders);

    // This is the x scale which is a type of scale called "band scale". This provides the seperation between bars
    const x = d3
        .scaleBand()
        .domain(data.map(item => item.name))
        .range([0, 500])
        .paddingInner(0.2)  // Margin inbetween bands on the x-axis
        .paddingOuter(0.2); // Margin between the first band and the y-axis and the last band and the side of the graph

    // Create a constant to represent all of the rectangles on the bar chart
    const rects = graph.selectAll('rect').data(data);

    // Create and update all rectangles that are hard coded into the DOM (most of the time there will be none, just good practice)
    rects
        .attr('width', x.bandwidth) 
        .attr('height', d => graphHeight - y(d.orders)) // The heigh of each rectangle is the difference between total graph height and the value of the order passed through the y linear scale
        .attr('fill', 'orange')
        .attr('x', d => x(d.name))
        .attr('y', d => y(d.orders)); // This is where the rectangle starts forming, from the TOP DOWN

    // Create and update all rectangles that are in the "enter selection" which is all of them usually
    rects
        .enter()
        .append('rect')
        .attr('width', x.bandwidth)
        .attr('height', d => graphHeight - y(d.orders)) // The heigh of each rectangle is the difference between total graph height and the value of the order passed through the y linear scale
        .attr('fill', 'orange')
        .attr('x', d => x(d.name))
        .attr('y', d => y(d.orders)); // This is where the rectangle starts forming, from the TOP DOWN

    // Create the x-axis itself using the d3 helper function
    const xAxis = d3.axisBottom(x);

    // Create the y-axis itself using the d3 helper function
    const yAxis = d3.axisLeft(y)
      .ticks(3) // How many ticks should be viewable on the axis
      .tickFormat(d => d + ' orders');  // This tells the user what each tick amount refers too

    // 
    xAxisGroup.call(xAxis); // Runs the xAxis function
    yAxisGroup.call(yAxis); // Runs the yAxis function

    // Modifies the lables on the x-Axis, incase more values are added to where things get cramped
    xAxisGroup.selectAll('text')
      .attr('transform', 'rotate(-40)')
      .attr('text-anchor', 'end');
});
