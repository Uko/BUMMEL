package net.unikernel.bummel.advanced_logic_elements

import java.util
import net.unikernel.bummel.advanced_logic_elements.Xor.Xor
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{BeforeAndAfter, FlatSpec}
import scala.collection.JavaConverters._

class XorSpec extends FlatSpec with ShouldMatchers with BeforeAndAfter {

 type Signals = util.TreeMap[String, java.lang.Double]

 var xor: Xor = _

  before {
    xor = new Xor
  }

  "A XOR element" should "return 000 for 000 input" in {
    val valuesOnPorts = new Signals {
      put("input1", 0)
      put("input2", 0)
      put("output", 0)
    }

    val result = xor.process(valuesOnPorts).asScala

    result("input1") should equal (0)
    result("input2") should equal (0)
    result("output") should equal (0)
  }

  it should "return 000 for 001 input" in {
    val valuesOnPorts = new Signals {
      put("input1", 0)
      put("input2", 0)
      put("output", 1)
    }

    val result = xor.process(valuesOnPorts).asScala

    result("input1") should equal (0)
    result("input2") should equal (0)
    result("output") should equal (0)
  }

  it should "return 001 for 010 input" in {
    val valuesOnPorts = new Signals {
      put("input1", 0)
      put("input2", 1)
      put("output", 0)
    }

    val result = xor.process(valuesOnPorts).asScala

    result("input1") should equal (0)
    result("input2") should equal (0)
    result("output") should equal (1)
  }

  it should "return 001 for 011 input" in {
    val valuesOnPorts = new Signals {
      put("input1", 0)
      put("input2", 1)
      put("output", 1)
    }

    val result = xor.process(valuesOnPorts).asScala

    result("input1") should equal (0)
    result("input2") should equal (0)
    result("output") should equal (1)
  }

  it should "return 001 for 100 input" in {
    val valuesOnPorts = new Signals {
      put("input1", 1)
      put("input2", 0)
      put("output", 0)
    }

    val result = xor.process(valuesOnPorts).asScala

    result("input1") should equal (0)
    result("input2") should equal (0)
    result("output") should equal (1)
  }

  it should "return 001 for 101 input" in {
    val valuesOnPorts = new Signals {
      put("input1", 1)
      put("input2", 0)
      put("output", 1)
    }

    val result = xor.process(valuesOnPorts).asScala

    result("input1") should equal (0)
    result("input2") should equal (0)
    result("output") should equal (1)
  }

  it should "return 000 for 110 input" in {
    val valuesOnPorts = new Signals {
      put("input1", 1)
      put("input2", 1)
      put("output", 0)
    }

    val result = xor.process(valuesOnPorts).asScala

    result("input1") should equal (0)
    result("input2") should equal (0)
    result("output") should equal (0)
  }

  it should "return 000 for 111 input" in {
    val valuesOnPorts = new Signals {
      put("input1", 1)
      put("input2", 1)
      put("output", 1)
    }

    val result = xor.process(valuesOnPorts).asScala

    result("input1") should equal (0)
    result("input2") should equal (0)
    result("output") should equal (0)
  }
}

