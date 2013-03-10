import org.scalatest.FlatSpec

class NorSpec extends FlatSpec {
  "A NOR element" should "return 001 for 000 input" in {
	val nor = new Nor()
	val valuesOnPorts = new TreeMap<>()
    valuesOnPorts.put("input1",0.)
    valuesOnPorts.put("input2",0.)
    valuesOnPorts.put("output",0.)
	val result = nor.process(valuesOnPorts)
	assert(result.get(0) === 0)
  }
}