package ws.prova.eventing;

public class Aggregator implements ProvaEventsAccumulator {

	private static final long serialVersionUID = -6070105850518061308L;

	private CountValue agg;
	
	private Aggregation processor;

	private class SumAggregation implements Aggregation {
		
		private static final long serialVersionUID = -5934127748617778629L;

		@Override
		public void process( CountValue cv, double value ) {
			cv.count++;
			cv.value += value;
		}
		
	}
	
	public Aggregator() {
		agg = new CountValue(0,0.0);
		this.processor = new SumAggregation();
	}
	
	public Aggregator(Aggregator aggregator) {
		this.agg = aggregator.agg;
		this.processor = aggregator.processor;
	}

	public CountValue process(double value) {
		processor.process(agg, value);
		return agg;
	}

	@Override
	public void clear() {
		agg.count = 0;
		agg.value = 0.0;
	}
	
	@Override
	public Aggregator clone() {
		return new Aggregator(this);
	}

	@Override
	public String toString() {
		return "Aggregator [agg=" + agg + "]";
	}

}
