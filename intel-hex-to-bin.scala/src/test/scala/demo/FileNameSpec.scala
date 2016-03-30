package demo

import org.scalatest._

/**
  * Unit tests for {@link FileName}
  */
class FileNameSpec extends FlatSpec with Matchers {

  it should "pass getBinFileNameTest" in {
    FileName.getBinFileName("test.hex") should be("test.bin")
  }

  it should "fail noNameFailureTest" in {
    a[IllegalArgumentException] should be thrownBy {
      FileName.getBinFileName(".hex")
    }
  }

  it should "fail noExtensionFailureTest" in {
    a[IllegalArgumentException] should be thrownBy {
      FileName.getBinFileName("test")
    }
  }

  it should "fail emptyExtensionFailureTest" in {
    a[IllegalArgumentException] should be thrownBy {
      FileName.getBinFileName("test.")
    }
  }

  it should "fail wrongExtensionFailureTest" in {
    a[IllegalArgumentException] should be thrownBy {
      FileName.getBinFileName("test.txt")
    }
  }

}